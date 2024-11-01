import java.io.*;

/**
 * Class: User
 * Class for handling a user
 *
 * @author: Tarik Guler, put your names here
 * @version: November 1st, 2024
 */

public class User {
    private String username;
    private String password;
    private String fileName;

    // Constructor that sets the filename,
    public User(String fileName, String username, String password) {
        this.fileName = fileName;
        this.username = username;
        this.password = password;

    }

    // Method for registering user, returns false if it doesn't work, returns true if it works
    // Creates a new file with name fileName and the first line: username,password
    public boolean register() throws IOException {
        boolean out;

        try {
            File f = new File(this.fileName);
            if (!f.createNewFile()) {
                System.out.printf("Error: file %s already exists%n", this.fileName);
                out = false;
            } else {
                FileOutputStream fos = new FileOutputStream(f);
                BufferedWriter bw = new BufferedWriter(new PrintWriter(fos, true));

                bw.write(String.format("%s,%s%n", this.username, this.password));
                bw.close();

                out = true;
            }
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }

        return out;
    }

    // Method that validates login, returns true if its valid, false if not
    public boolean login() throws IOException {
        boolean out;

        try {
            File f = new File(this.fileName);
            BufferedReader bfr = new BufferedReader(new FileReader(f));

            String[] data = bfr.readLine().split(",");

            if (data.length != 2 || !data[0].equals(this.username) ||
                !data[1].equals(this.password)) {
                out = false;
            } else {
                out = true;
            }

            bfr.close();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        } catch (Exception e) {
            out = false;
        }

        return out;
    }
}
