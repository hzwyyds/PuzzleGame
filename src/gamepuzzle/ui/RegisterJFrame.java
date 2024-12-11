package gamepuzzle.ui;

import gamepuzzle.domain.User;
import gamepuzzle.util.FrameUtil;
import gamepuzzle.util.UserUtil;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class RegisterJFrame extends JFrame implements MouseListener {

    ArrayList<User> userList = UserUtil.readFromFile();

    JTextField registerUsername = new JTextField();
    JPasswordField registerPassword = new JPasswordField();
    JPasswordField registerConfirmPassword = new JPasswordField();

    JButton registerButton = new JButton();
    JButton resetButton = new JButton();
    JButton loginButton = new JButton();
    JButton showPasswordButton = new JButton();


    public RegisterJFrame(){

        initFrame();

        initView();
    }

    private void initFrame() {

        this.setSize(488, 430);
        this.setTitle("拼图游戏 V1.0--注册");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);
        this.setLayout(null);
    }

    private void initView() {
        JLabel registerUsernameText = new JLabel(new ImageIcon("image/register/注册用户名.png"));
        registerUsernameText.setBounds(85, 135, 80, 17);
        this.getContentPane().add(registerUsernameText);

        registerUsername.setBounds(195, 130, 200, 30);
        this.getContentPane().add(registerUsername);

        JLabel registerPasswordText = new JLabel(new ImageIcon("image/register/注册密码.png"));
        registerPasswordText.setBounds(85, 195, 80, 17);
        this.getContentPane().add(registerPasswordText);

        registerPassword.setBounds(195, 190, 200, 30);
        this.getContentPane().add(registerPassword);

        JLabel registerConfirmPasswordText = new JLabel(new ImageIcon("image/register/再次输入密码.png"));
        registerConfirmPasswordText.setBounds(75, 255, 100, 17);
        this.getContentPane().add(registerConfirmPasswordText);

        registerConfirmPassword.setBounds(195, 250, 200, 30);
        this.getContentPane().add(registerConfirmPassword);

        showPasswordButton.setBounds(400, 190, 20, 30);
        showPasswordButton.setIcon(new ImageIcon("image/login/显示密码.png"));
        showPasswordButton.setBorderPainted(false);
        showPasswordButton.setContentAreaFilled(false);
        showPasswordButton.addMouseListener(this);
        this.getContentPane().add(showPasswordButton);

        registerButton.setBounds(330, 310, 128, 47);
        registerButton.setIcon(new ImageIcon("image/register/注册按钮.png"));
        registerButton.setBorderPainted(false);
        registerButton.setContentAreaFilled(false);
        registerButton.addMouseListener(this);
        this.getContentPane().add(registerButton);

        resetButton.setBounds(30, 310, 128, 47);
        resetButton.setIcon(new ImageIcon("image/register/重置按钮.png"));
        resetButton.setBorderPainted(false);
        resetButton.setContentAreaFilled(false);
        resetButton.addMouseListener(this);
        this.getContentPane().add(resetButton);

        loginButton.setBounds(180, 310, 128, 47);
        loginButton.setIcon(new ImageIcon("image/login/登录按钮.png"));
        loginButton.setBorderPainted(false);
        loginButton.setContentAreaFilled(false);
        loginButton.addMouseListener(this);
        this.getContentPane().add(loginButton);

        JLabel background = new JLabel(new ImageIcon("image/register/background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);



        this.setVisible(true);
    }



    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == registerButton) {
            System.out.println("注册按钮被点击");
            String username = registerUsername.getText();
            String password = registerPassword.getText();
            String confirmPassword = registerConfirmPassword.getText();
            if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                FrameUtil.showJDialog("用户名或密码不能为空", 0.8);
            } else if (UserUtil.checkUsernameUniqueness(userList, username)) {
                FrameUtil.showJDialog("用户名已存在", 0.8);
            } else if (!UserUtil.checkPassword(password)) {
                FrameUtil.showJDialog("密码不合法！应为6-15位\n且不能有空格", 0.8);
            } else if (!password.equals(confirmPassword)) {
                FrameUtil.showJDialog("两次输入的密码不一致", 0.8);
            } else {
                User tempUser = new User(username, password);
                userList.add(tempUser);
                UserUtil.userListToTxt(userList);
                FrameUtil.showJDialog("注册成功",0.8);
                this.dispose();
                new LoginJFrame();
            }

        } else if (e.getSource() == resetButton) {
            registerUsername.setText("");
            registerPassword.setText("");
            registerConfirmPassword.setText("");
        } else if (e.getSource() == loginButton) {
            System.out.println("登录按钮被点击");
            this.dispose();
            new LoginJFrame();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == registerButton) {
            registerButton.setIcon(new ImageIcon("image/register/注册按下.png"));
        } else if (e.getSource() == resetButton) {
            resetButton.setIcon(new ImageIcon("image/register/重置按下.png"));
        } else if (e.getSource() == loginButton) {
            loginButton.setIcon(new ImageIcon("image/login/登录按下.png"));
        } else if (e.getSource() == showPasswordButton) {
            showPasswordButton.setIcon(new ImageIcon("image/login/显示密码按下.png"));
            registerPassword.setEchoChar((char) 0);
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == registerButton) {
            registerButton.setIcon(new ImageIcon("image/register/注册按钮.png"));
        }else if (e.getSource() == resetButton) {
            resetButton.setIcon(new ImageIcon("image/register/重置按钮.png"));
        }else if (e.getSource() == loginButton) {
            loginButton.setIcon(new ImageIcon("image/login/登录按钮.png"));
        } else if (e.getSource() == showPasswordButton) {
            showPasswordButton.setIcon(new ImageIcon("image/login/显示密码.png"));
            registerPassword.setEchoChar('•');
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}