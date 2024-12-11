package gamepuzzle.util;

import gamepuzzle.domain.User;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;

public class UserUtil {
    //从文本文件中读取用户名和密码并存入ArrayList
    public static ArrayList<User> readFromFile() {

        String filename = "image/user.txt";
        ArrayList<User> users = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\s+");
                if (parts.length == 2) {
                    users.add(new User(parts[0], parts[1]));
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    /**
     * 判断用户名是否被注册
     * @param userList 用户列表
     * @param username 用户名
     * @return true 被注册了 false 没有被注册
     */
    public static boolean checkUsernameUniqueness(ArrayList<User> userList, String username) {
        for (User user : userList) {
            if (user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断密码设置是否合理,密码需6-15位,密码不能有空格
     * @return true 一致 false 不一致
     */
    public static boolean checkPassword(String password) {
        return password.length() >= 6 && password.length() <= 15 && !password.contains(" ");
    }

    /**
     * 判断用户名和密码是否匹配
     * @param userList 用户列表
     * @param userInput 用户输入的用户名和密码
     * @return true 匹配 false 不匹配
     */
    public static boolean contains(ArrayList<User> userList, User userInput) {
        for (User tempUser : userList) {
            if (userInput.getUsername().equals(tempUser.getUsername()) && userInput.getPassword().equals(tempUser.getPassword())) {
                return true;
            }
        }
        return false;
    }

    //把用户名和密码写入一个文本文件
    public static void userListToTxt(ArrayList<User> userList) {
        String filename = "image/user.txt";
        try (FileWriter writer = new FileWriter(filename)) {
            for (User user : userList) {
                writer.write(user.getUsername() + "\t" + user.getPassword() + "\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
