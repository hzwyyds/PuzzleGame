package gamepuzzle.util;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;


public class FrameUtil {
    /**
     * 显示一个提示框
     * @param content 弹窗内容
     * @param time 弹窗显示时间，秒
     */
    public static void showJDialog(String content, double time) {
        JDialog jd = new JDialog();
        jd.setLayout(null);
        jd.setSize(200, 160);
        jd.setAlwaysOnTop(true);
        jd.setLocationRelativeTo(null);
        jd.setModal(true);

        // 使用HTML标签来实现文字换行，将content包裹在 <html><body> 标签内，并将换行处替换为 <br> 标签
        String htmlContent = "<html><body>" + content.replaceAll("\n", "<br>") + "</body></html>";
        JLabel warning = new JLabel(htmlContent);
        warning.setBounds(25, 0, 150, 60);
        jd.getContentPane().add(warning);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                jd.dispose();
            }
        }, (int)(time * 1000L));

        jd.setVisible(true);
    }

}
