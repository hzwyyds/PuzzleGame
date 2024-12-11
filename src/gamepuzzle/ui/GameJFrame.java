package gamepuzzle.ui;

import gamepuzzle.util.FrameUtil;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.*;
import java.util.Random;
import java.util.TimerTask;

public class GameJFrame extends JFrame implements ActionListener, KeyListener {

    int[][] data = new int[4][4];

    //用于记录空白块位置
    int x = 3;
    int y = 3;

    //用于记录当前图片路径
    String galleryPath = "image/animal/animal";
    int stage = 1;

    //统计步数
    int step = 0;

    JLabel promptLabel = new JLabel();

    //菜单条目
    JMenuItem replayItem = new JMenuItem("重新开始");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem animalGalleryItem = new JMenuItem("动物");
    JMenuItem girlGalleryItem = new JMenuItem("美女");
    JMenuItem sportGalleryItem = new JMenuItem("运动");

    JMenuItem accountItem = new JMenuItem("游戏教程");


    public GameJFrame() {

        //初始化界面
        initFrame();
        //菜单界面
        initMenu();
        //初始化数据
        initImageData();
        //初始化图片
        initImage();

        this.setVisible(true);
    }

    /**
     * 初始化图片数据
     */
    private void initImageData() {
        int[] tempArr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 0};

        Random r = new Random();
        //遍历数组，随机交换
        for (int i = 0; i < tempArr.length - 1; i++) {
            int index = r.nextInt(tempArr.length - 1);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        //将数组赋值给二维数组
        for (int i = 0; i < tempArr.length; i++) {
            data[i / 4][i % 4] = tempArr[i];
        }
    }

    /**
     * 初始化图片
     */
    private void initImage() {

        this.getContentPane().removeAll();

        if (isWin()) {
            JLabel winJLabel = new JLabel(new ImageIcon("image/win.png"));
            winJLabel.setBounds(203, 283, 197, 73);
            this.getContentPane().add(winJLabel);
        }

        JLabel stepCount = new JLabel("步数：" + step);
        stepCount.setBounds(50, 30, 100, 20);
        this.getContentPane().add(stepCount);

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                int num = data[i][j];
                ImageIcon icon = new ImageIcon(galleryPath + stage + "/" + num + ".jpg");
                JLabel jLabel = new JLabel(icon);

                jLabel.setBounds(105 * j + 83, 105 * i + 134, 105, 105);
                //给图片添加边框
                jLabel.setBorder(new BevelBorder(BevelBorder.LOWERED));
                this.getContentPane().add(jLabel);
            }
        }

        //添加背景图片
        JLabel background = new JLabel(new ImageIcon("image/background.png"));
        background.setBounds(40, 40, 508, 560);
        this.getContentPane().add(background);
        this.getContentPane().repaint();
        this.requestFocusInWindow();
    }

    /**
     * 初始化菜单
     */
    private void initMenu() {
        JMenuBar jMenuBar = new JMenuBar();

        JMenu functionMenu = new JMenu("功能");
        JMenu galleryMenu = new JMenu("切换图库");
        JMenu aboutMenu = new JMenu("关于");

        functionMenu.add(replayItem);
        functionMenu.add(reLoginItem);
        functionMenu.add(closeItem);

        galleryMenu.add(animalGalleryItem);
        galleryMenu.add(girlGalleryItem);
        galleryMenu.add(sportGalleryItem);

        aboutMenu.add(accountItem);

        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        animalGalleryItem.addActionListener(this);
        girlGalleryItem.addActionListener(this);
        sportGalleryItem.addActionListener(this);
        accountItem.addActionListener(this);

        jMenuBar.add(functionMenu);
        jMenuBar.add(galleryMenu);
        jMenuBar.add(aboutMenu);
        this.setJMenuBar(jMenuBar);
    }

    /**
     * 初始化界面
     */
    private void initFrame() {
        //设置界面大小
        this.setSize(603, 680);
        this.setTitle("拼图单机版 v1.0");
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);

        //设置点击关闭时退出程序
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //取消默认居中放置，使用绝对布局
        this.setLayout(null);

        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //一直按着按键调用该方法
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == 65) {
            //移除当前所有图片
            this.getContentPane().removeAll();
            //加载一张完整图片
            JLabel allImage = new JLabel(new ImageIcon(galleryPath + stage + "/" + "all.jpg"));
            allImage.setBounds(83, 134, 420, 420);
            this.getContentPane().add(allImage);
            JLabel background = new JLabel(new ImageIcon("image/background.png"));
            background.setBounds(40, 40, 508, 560);
            this.getContentPane().add(background);

            //刷新界面
            this.getContentPane().repaint();
        }
    }

    //松开按键调用该方法
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        boolean isWinNow = isWin();
        if (code == 37 || code == 38 || code == 39 || code == 40) {
            if (isWinNow) {
                FrameUtil.showJDialog("恭喜你，拼图成功!\n不需要操作啦！", 0.5);
            }else {
                moveBlock(code);
            }
        }else {
            handleOtherKey(code);
        }
    }

    /**
     * 处理其他按键
     * @param code
     */
    private void handleOtherKey(int code) {
        if (code == 65) {
            // 松开A键刷新
            initImage();
        } else if (code == 83) {
            // S键 作弊码
            data = new int[][]{
                    {1, 2, 3, 4},
                    {5, 6, 7, 8},
                    {9, 10, 11, 12},
                    {13, 14, 15, 0}
            };
            x = 3;
            y = 3;
            initImage();
        } else if (code == 49) {
            // 1键 重新开始
            step = 0;
            initImageData();
            initImage();
        } else if (code == 50) {
            // 2键 上一关
            step = 0;
            x = 3;
            y = 3;
            if (stage == 1) {
                FrameUtil.showJDialog("第一关啦！",0.5);
                return;
            }
            stage--;
            initImageData();
            initImage();
            this.getContentPane().repaint();
        } else if (code == 51) {
            // 3键 下一关
            step = 0;
            x = 3;
            y = 3;
            int maxStage = 0;
            if (galleryPath.equals("image/girl/girl")) {
                maxStage = 13;
            } else if (galleryPath.equals("image/animal/animal")) {
                maxStage = 8;
            } else if (galleryPath.equals("image/sport/sport")) {
                maxStage = 10;
            }
            if (stage == maxStage) {
                FrameUtil.showJDialog("最后一关啦！",0.5);
                return;
            }
            stage++;
            initImageData();
            initImage();
            this.getContentPane().repaint();
        }
    }

    /**
     * 拼图操作
     * @param code
     */
    private void moveBlock(int code) {
        if (code == 37) {
            System.out.println("向左移动");
            if (y == 3) {
                return;
            }
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            // 步数加一
            step++;
            initImage();
        } else if (code == 38) {
            System.out.println("向上移动");
            if (x == 3) {
                return;
            }
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            step++;
            initImage();
        } else if (code == 39) {
            System.out.println("向右移动");
            if (y == 0) {
                return;
            }
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            step++;
            initImage();
        } else if (code == 40) {
            System.out.println("向下移动");
            if (x == 0) {
                return;
            }
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            step++;
            initImage();
        }
    }

    /**
     * 获胜判断
     * @return true表示胜利
     */
    private boolean isWin() {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if (data[i][j] != (i * 4 + j + 1) % 16) {
                    return false;
                }
            }
        }
        return true;
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        //获取当前被点击的条目
        Object obj = e.getSource();
        if (obj == replayItem) {
            System.out.println("重新开始");
            replayGame();
        } else if (obj == reLoginItem) {
            System.out.println("重新登录");

            this.setVisible(false);

            new LoginJFrame();
        } else if (obj == closeItem) {
            System.out.println("退出游戏");

            System.exit(0);
        } else if (obj == accountItem) {
            System.out.println("教程");

            //弹窗
            JDialog jDialog = new JDialog();

            JLabel accountJLabel = new JLabel(new ImageIcon("image/document.png"));
            accountJLabel.setBounds(0, 0, 300, 300);
            jDialog.getContentPane().add(accountJLabel);
            jDialog.setSize(500, 400);
            jDialog.setAlwaysOnTop(true);
            jDialog.setLocationRelativeTo(null);
            jDialog.setModal(true);
            jDialog.setVisible(true);
        } else if (obj == animalGalleryItem) {
            if (galleryPath.equals("image/animal/animal")) {
                return;
            }
            galleryPath = "image/animal/animal";
            stage = 1;
            replayGame();
        } else if (obj == girlGalleryItem) {
            if (galleryPath.equals("image/girl/girl")) {
                return;
            }
            galleryPath = "image/girl/girl";
            stage = 1;
            replayGame();
        } else if (obj == sportGalleryItem) {
            if (galleryPath.equals("image/sport/sport")) {
                return;
            }
            galleryPath = "image/sport/sport";
            stage = 1;
            replayGame();
        }
    }

    private void replayGame() {
        step = 0;
        x = 3;
        y = 3;
        initImageData();
        initImage();
    }

}
