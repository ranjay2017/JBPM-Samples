/*
 * DesktopApplication1View.java
 */

package org.plugtree.training.jbpm.humantasks.client.ui;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.jbpm.task.User;
import org.jbpm.task.query.TaskSummary;
import org.jdesktop.application.Action;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.SingleFrameApplication;
import org.plugtree.training.jbpm.humantasks.client.core.HumanTaskClient;

/**
 * The application's main frame.
 */
public class ApplicationView extends FrameView {

    private DefaultListModel usersListModel;
    private HumanTaskClient client;
    private Map<String, User> users = new HashMap<String, User>();
    private DefaultListModel pendingTaskListModel;
    private Map<String, TaskSummary> tasks = new HashMap<String, TaskSummary>();

    public ApplicationView(SingleFrameApplication app) {
        super(app);

        initComponents();

        MouseListener mouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() >= 1)  {
                    int position = usersList.locationToIndex(e.getPoint());
                    String userName = (String)usersListModel.getElementAt(position);
                    System.out.println("position: " + position + " UserName: " + userName);
                    refreshUserAssignedTasks(userName);
                }
            }
        };
        usersList.addMouseListener(mouseListener);

        initializeUsers();

        pendingTaskListModel = new DefaultListModel();
        pendingTasksList.setModel(pendingTaskListModel);

        client = new HumanTaskClient();
        try {
            client.start();
        } catch (RuntimeException ex) {
            Logger.getLogger(ApplicationView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Execution Error", JOptionPane.ERROR_MESSAGE);
            Application.getApplication().exit();
        }

    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = Application.getApplication().getMainFrame();
            aboutBox = new ApplicationAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        Application.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        usersList = new javax.swing.JList();
        startProcessButton = new javax.swing.JButton();
        completeTaskButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        pendingTasksList = new javax.swing.JList();
        pendingTasksLabel = new javax.swing.JLabel();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();

        mainPanel.setName("mainPanel"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        usersList.setName("pendingTaskList"); // NOI18N
        jScrollPane1.setViewportView(usersList);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(org.plugtree.training.jbpm.humantasks.client.ui.Application.class).getContext().getActionMap(ApplicationView.class, this);
        startProcessButton.setAction(actionMap.get("startProcessAction")); // NOI18N
        startProcessButton.setName("startProcessButton"); // NOI18N
        startProcessButton.setText("Start Process");
        
        completeTaskButton.setAction(actionMap.get("completeTaskAction")); // NOI18N
        completeTaskButton.setName("completeTaskButton"); // NOI18N
		completeTaskButton.setText("Complete Task");
		completeTaskButton.setEnabled(false);
        
        pendingTasksLabel.setText("Pending Tasks:");

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        pendingTasksList.setName("pendingTasksList"); // NOI18N
        jScrollPane2.setViewportView(pendingTasksList);

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(org.plugtree.training.jbpm.humantasks.client.ui.Application.class).getContext().getResourceMap(ApplicationView.class);
        pendingTasksLabel.setText(resourceMap.getString("pendingTasksLabel.text")); // NOI18N
        pendingTasksLabel.setName("pendingTasksLabel"); // NOI18N

        org.jdesktop.layout.GroupLayout mainPanelLayout = new org.jdesktop.layout.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, mainPanelLayout.createSequentialGroup()
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, mainPanelLayout.createSequentialGroup()
                        .add(29, 29, 29)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 156, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 54, Short.MAX_VALUE)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 156, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(mainPanelLayout.createSequentialGroup()
                        .addContainerGap(244, Short.MAX_VALUE)
                        .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(pendingTasksLabel)
                            .add(startProcessButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 151, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(completeTaskButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 151, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .add(31, 31, 31))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .add(17, 17, 17)
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 183, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(mainPanelLayout.createSequentialGroup()
                        .add(startProcessButton)
                        .add(8, 8, 8)
                        .add(pendingTasksLabel)
                        .add(18, 18, 18)
                        .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 183, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                        .add(8, 8, 8)
						.add(completeTaskButton)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        setComponent(mainPanel);
        setMenuBar(menuBar);
    }// </editor-fold>//GEN-END:initComponents

    @Action
    public void startProcessAction() {
        try {
            client.startProcess();
        } catch (RuntimeException ex) {
            Logger.getLogger(ApplicationView.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Execution Error", JOptionPane.ERROR_MESSAGE);
        }
        String userName = (String) usersList.getSelectedValue();
        if (userName!=null) {
            refreshUserAssignedTasks(userName);
        }
    }
    
    @Action
    public void completeTaskAction() {
        String taskKey = (String) pendingTasksList.getSelectedValue();
        TaskSummary task = tasks.get(taskKey);
        String userKey = (String) usersList.getSelectedValue();
        System.out.println("complete task user: " + userKey);
        User user = users.get(userKey);
        client.completeTask(user, task);
        JOptionPane.showMessageDialog(null, "Task Completed.\nName: " + task.getName() + "\nID: " + task.getId() , "Information", JOptionPane.INFORMATION_MESSAGE);
        refreshUserAssignedTasks(user.getId());
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JLabel pendingTasksLabel;
    private javax.swing.JList pendingTasksList;
    private javax.swing.JButton startProcessButton;
    private javax.swing.JButton completeTaskButton;
    private javax.swing.JList usersList;
    // End of variables declaration//GEN-END:variables

    private JDialog aboutBox;

    private void initializeUsers() {
        users.put("salaboy", new User("salaboy"));
        users.put("translator", new User("translator"));
        users.put("reviewer", new User("reviewer"));
        users.put("Administrator", new User("Administrator"));
        usersListModel = new DefaultListModel();
        usersList.setModel(usersListModel);
        Set<String> userKeys = users.keySet();
        for (String key : userKeys) {
            usersListModel.addElement(key);
        }
    }

    private void refreshUserAssignedTasks(String userName) {
        User user = users.get(userName);
        List<TaskSummary> taskSummarys = null;
        try {
            taskSummarys = client.getAssignedTasks(user);
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "An error happened trying to retrieve the user pending tasks.\n" +
                                                "Maybe the task server is down?", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        System.out.println("tasks size: " + taskSummarys.size());
        tasks.clear();
        pendingTasksList.removeAll();
        pendingTaskListModel.removeAllElements();
        for (TaskSummary taskSummary : taskSummarys) {
            tasks.put(taskSummary.getId() + " - " + taskSummary.getName(), taskSummary);
            pendingTaskListModel.addElement(taskSummary.getId() + " - " + taskSummary.getName());
        }
        if (tasks.size() > 0) {
            pendingTasksList.setSelectedIndex(0);
            completeTaskButton.setEnabled(true);
        }
        else {
            completeTaskButton.setEnabled(false);
        }
    }

}
