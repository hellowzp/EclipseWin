package cn.edu.nwsuaf.videoCapture.utils;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

/**
 * 
 * @author Qzhong
 *
 */
public class SwingUtils {

    /**
     * ��ָ����button���ӻس������¼��Ĺ���
     * @param button
     */
    public static void enterPressesWhenFocused(JButton button) {
        button.registerKeyboardAction(button.getActionForKeyStroke(KeyStroke
                .getKeyStroke(KeyEvent.VK_SPACE, 0, false)),
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_FOCUSED);
    }

    /**
     * ���ı�������лس������¼�
     * @param textField
     * @param actionListener
     */
	public static void enterPressesWhenFocused(JTextField textField,
            ActionListener actionListener) {
        textField.registerKeyboardAction(actionListener,
                KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false),
                JComponent.WHEN_FOCUSED);
    }

}