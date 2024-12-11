package gamepuzzle.ui;

import gamepuzzle.domain.User;
import gamepuzzle.util.CodeGenerate;
import gamepuzzle.util.FrameUtil;
import gamepuzzle.util.UserUtil;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;

public class LoginJFrame extends JFrame implements MouseListener {

    ArrayList<User> userList = UserUtil.readFromFile();


    JButton login = new JButton();
    JButton register = new JButton();
    JButton showPasswordButton = new JButton();

    JTextField username = new JTextField();

    JPasswordField password = new JPasswordField();
    JTextField code = new JTextField();

    JLabel rightCode = new JLabel();
    /**
     * 构造方法
     */
    public LoginJFrame() {

        initJFrame();

        initView();

        this.setVisible(true);

    }

    private void initView() {
        //用户名文字
        JLabel usernameText = new JLabel(new ImageIcon("image/login/用户名.png"));
        usernameText.setBounds(116, 135, 47, 17);
        this.getContentPane().add(usernameText);

        //用户名输入框
        username.setBounds(195, 135, 200, 30);
        this.getContentPane().add(username);

        //密码文字
        JLabel passwordText = new JLabel(new ImageIcon("image/login/密码.png"));
        passwordText.setBounds(130, 195, 32, 17);
        this.getContentPane().add(passwordText);

        //密码输入框
        password.setBounds(195, 195, 200, 30);
        this.getContentPane().add(password);

        //验证码提示
        JLabel codeText = new JLabel(new ImageIcon("image/login/验证码.png"));
        codeText.setBounds(133, 256, 50, 30);
        this.getContentPane().add(codeText);

        //验证码输入框
        code.setBounds(195, 256, 100, 30);
        this.getContentPane().add(code);

        String codeStr = CodeGenerate.generateCode();
        rightCode.setText(codeStr);
        rightCode.addMouseListener(this);

        rightCode.setBounds(300, 256, 50, 30);
        this.getContentPane().add(rightCode);

        //登录按钮
        login.setBounds(100, 310, 128, 47);
        login.setIcon(new ImageIcon("image/login/登录按钮.png"));
        //去除按钮边框
        login.setBorderPainted(false);
        //去除按钮背景
        login.setContentAreaFilled(false);

        login.addMouseListener(this);
        this.getContentPane().add(login);

        //注册按钮
        register.setBounds(270, 310, 128, 47);
        register.setIcon(new ImageIcon("image/login/注册按钮.png"));

        register.setBorderPainted(false);
        register.setContentAreaFilled(false);
        register.addMouseListener(this);
        this.getContentPane().add(register);

        showPasswordButton.setBounds(400, 195, 20, 30);
        showPasswordButton.setIcon(new ImageIcon("image/login/显示密码.png"));
        showPasswordButton.setBorderPainted(false);
        showPasswordButton.setContentAreaFilled(false);
        showPasswordButton.addMouseListener(this);
        this.getContentPane().add(showPasswordButton);

        JLabel background = new JLabel(new ImageIcon("image/login/background.png"));
        background.setBounds(0, 0, 470, 390);
        this.getContentPane().add(background);
    }

    private void initJFrame() {
        this.setSize(488, 430);
        this.setTitle("拼图游戏 V1.0--登录");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);//居中
        this.setAlwaysOnTop(true);
        this.setLayout(null);
    }


    //点击操作
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource() == login) {
            System.out.println("点击了登录按钮");

            String usernameInput = username.getText();
            String passwordInput = password.getText();
            String codeInput = code.getText();

            User userInfo = new User(usernameInput, passwordInput);

            if (usernameInput.isEmpty() || passwordInput.isEmpty()) {
                FrameUtil.showJDialog("用户名或密码不能为空", 0.8);
            } else if (codeInput.isEmpty()) {
                FrameUtil.showJDialog("验证码不能为空", 0.8);
            } else if (!codeInput.equalsIgnoreCase(rightCode.getText())) {
                FrameUtil.showJDialog("验证码错误", 0.8);
            } else if (UserUtil.contains(userList, userInfo)) {
                FrameUtil.showJDialog("登录成功",0.2);
                this.setVisible(false);
                new GameJFrame();
            }else {
                FrameUtil.showJDialog("用户名或密码错误", 0.8);
            }
        } else if (e.getSource() == register) {
            System.out.println("点击了注册按钮");
            this.setVisible(false);
            new RegisterJFrame();
        } else if (e.getSource() == rightCode) {
            System.out.println("更换验证码");
            String code = CodeGenerate.generateCode();
            rightCode.setText(code);
        }
    }

    //按下不送时操作
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("image/login/登录按下.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image/login/注册按下.png"));
        } else if (e.getSource() == showPasswordButton) {
            showPasswordButton.setIcon(new ImageIcon("image/login/显示密码按下.png"));
            password.setEchoChar((char) 0);
        }
    }

    //松开鼠标时操作
    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getSource() == login) {
            login.setIcon(new ImageIcon("image/login/登录按钮.png"));
        } else if (e.getSource() == register) {
            register.setIcon(new ImageIcon("image/login/注册按钮.png"));
        } else if (e.getSource() == showPasswordButton) {
            showPasswordButton.setIcon(new ImageIcon("image/login/显示密码.png"));
            password.setEchoChar('•');
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

}
