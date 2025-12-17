package Utils;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class CsvReader {

    // Đọc file CSV và trả về danh sách các dòng dữ liệu
    public static List<String[]> readCsv(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Bỏ qua dòng header (dòng đầu tiên)
            br.readLine();

            // Đọc từng dòng
            while ((line = br.readLine()) != null) {
                // Tách dữ liệu theo dấu phẩy
                String[] values = line.split(",");
                data.add(values);
            }
        }

        return data;
    }

    // Đọc file CSV và trả về danh sách LoginData
    public static List<LoginData> readLoginData(String filePath) throws IOException {
        List<LoginData> loginDataList = new ArrayList<>();
        List<String[]> rows = readCsv(filePath);

        for (String[] row : rows) {
            if (row.length >= 2) {
                String username = row[0].trim();
                String password = row[1].trim();
                Boolean expected = row[2].equals("true")?true:false;
                loginDataList.add(new LoginData(username, password,expected));
            }
        }

        return loginDataList;
    }
}
