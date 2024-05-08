


import java.sql.*;
import java.util.ArrayList;
public class DBManager {
    public static final String url = "jdbc:postgresql://localhost/JavaProject";// Input the name of YOUR db
    public static final String user = "postgres";
    public static final String password = "Alim1234";
    public static Connection connection;

    public void connect(){
        try{
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection to Postgresql server was successfull");
        }catch (Exception e){
            System.out.println("ERROR with connection");
            e.printStackTrace();
        }
    }


    public ArrayList<Clothes> getAllClothes(){
        ArrayList<Clothes> clothes = new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement("" +
                    "SELECT * FROM clothes");
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Long id = resultSet.getLong("id");
                String title = resultSet.getString("title");
                String desc = resultSet.getString("description");
                int price = resultSet.getInt("price");
                String type = resultSet.getString("type");
                int quantity = resultSet.getInt("quantity");
                boolean available = resultSet.getBoolean("available");

                ArrayList<String> sizes = getSizesForCloth(id);
                ArrayList<String> colors = getColorsForCloth(id);

                Clothes cloth=new Clothes(id, title, desc, price, type, sizes, colors, quantity, available);
                clothes.add(cloth);

            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return clothes;
    }


    public int findOrCreateSize(String size) throws SQLException {
        int sizeId = -1;
        PreparedStatement statement = connection.prepareStatement(
                "SELECT id FROM Sizes WHERE title = ?");
        statement.setString(1, size);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            sizeId = resultSet.getInt("id");
        } else {
            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO Sizes (title) VALUES (?) RETURNING id");
            insertStatement.setString(1, size);
            ResultSet insertResult = insertStatement.executeQuery();
            if (insertResult.next()) {
                sizeId = insertResult.getInt(1);
            }
            insertResult.close();
            insertStatement.close();
        }
        resultSet.close();
        statement.close();
        return sizeId;
    }

    public int findOrCreateColor(String color) throws SQLException {
        int colorId = -1;
        PreparedStatement statement = connection.prepareStatement(
                "SELECT id FROM Colors WHERE title = ?");
        statement.setString(1, color);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            colorId = resultSet.getInt("id");
        } else {

            PreparedStatement insertStatement = connection.prepareStatement(
                    "INSERT INTO Colors (title) VALUES (?) RETURNING id");
            insertStatement.setString(1, color);
            ResultSet insertResult = insertStatement.executeQuery();
            if (insertResult.next()) {
                colorId = insertResult.getInt(1);
            }
            insertResult.close();
            insertStatement.close();
        }
        resultSet.close();
        statement.close();
        return colorId;
    }

    public void addCloth(Clothes cloth) throws SQLException {
        PreparedStatement clothStatement = connection.prepareStatement(
                "INSERT INTO clothes (title, description, price, type, quantity, available) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        clothStatement.setString(1, cloth.getTitle());
        clothStatement.setString(2, cloth.getDesc());
        clothStatement.setInt(3, cloth.getPrice());
        clothStatement.setString(4, cloth.getType());
        clothStatement.setInt(5, cloth.getQuantity());
        clothStatement.setBoolean(6, cloth.getAvailable());
        clothStatement.executeUpdate();

        ResultSet generatedKeys = clothStatement.getGeneratedKeys();
        int clothId = -1;
        if (generatedKeys.next()) {
            clothId = generatedKeys.getInt(1);
        } else {
            throw new SQLException("Failed to get the generated key for Clothes");
        }
        generatedKeys.close();
        clothStatement.close();

        for (String size : cloth.getSizes()) {
            int sizeId = findOrCreateSize(size);
            PreparedStatement clothesSizesStatement = connection.prepareStatement(
                    "INSERT INTO ClothesSizes (clothes_id, size_id) VALUES (?, ?)");
            clothesSizesStatement.setInt(1, clothId);
            clothesSizesStatement.setInt(2, sizeId);
            clothesSizesStatement.executeUpdate();
            clothesSizesStatement.close();
        }

        for (String color : cloth.getColors()) {
            int colorId = findOrCreateColor(color);
            PreparedStatement clothesColorsStatement = connection.prepareStatement(
                    "INSERT INTO ClothesColors (clothes_id, color_id) VALUES (?, ?)");
            clothesColorsStatement.setInt(1, clothId);
            clothesColorsStatement.setInt(2, colorId);
            clothesColorsStatement.executeUpdate();
            clothesColorsStatement.close();
        }
    }


    private static ArrayList<String> getSizesForCloth(Long clothId) {
        ArrayList<String> sizes = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT size_id FROM ClothesSizes WHERE clothes_id = ?");
            statement.setLong(1, clothId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long sizeId = resultSet.getLong("size_id");
                String size = getSizeById(sizeId);
                sizes.add(size);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return sizes;
    }

    private static String getSizeById(Long sizeId) {
        String size = "";

        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT title FROM Sizes WHERE id = ?");
            statement.setLong(1, sizeId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                size = resultSet.getString("title");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return size;
    }

    private static ArrayList<String> getColorsForCloth(Long clothId) {
        ArrayList<String> colors = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT color_id FROM ClothesColors WHERE clothes_id = ?");
            statement.setLong(1, clothId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Long colorId = resultSet.getLong("color_id");
                String color = getColorById(colorId);
                colors.add(color);
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return colors;
    }

    private static String getColorById(Long colorId) {
        String color = "";
        try {
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT title FROM Colors WHERE id = ?");
            statement.setLong(1, colorId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                color = resultSet.getString("title");
            }
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return color;
    }



}
