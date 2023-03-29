package eth.cmh.cmh;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import jfxtras.scene.control.CalendarPicker;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.api.errors.GitAPIException;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class CMHController {
    public Button createrepo;
    public CheckBox randomize;
    public CalendarPicker calendarpicker;
    public Button populate;
    public ListView<Calendar> listview;
    public TextField textfield;
    public TextField tokenfield;
    public Label status;
    public TextField emailfield;
    private Git git;
    private File file;

    public void initialize() {

        listview.setItems(calendarpicker.calendars());
        listview.setCellFactory(lv -> new ListCell<>(){
            private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            @Override
            protected void updateItem(Calendar item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("");
                } else {
                    setText(formatter.format(LocalDate.ofInstant(item.toInstant(), ZoneId.systemDefault())));
                }
            }
        });


    }

    @FXML
    protected void createrepoAction(ActionEvent actionEvent) throws GitAPIException, IOException {
        // Initialize a new Git repository
        String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
        File folder = new File(desktopPath + File.separator + "CMHfolder");
        if (!folder.exists()) {
            folder.mkdirs();
        }
        String folderPath = folder.getAbsolutePath();

        this.git = Git.init().setDirectory(new File(folderPath)).call();

        // Create a new file in the repository, and add some data (a dot)
        this.file = new File(folderPath+"/temp.txt");
        this.file.getParentFile().mkdirs();
        FileWriter writer = new FileWriter(this.file);
        writer.write(".");
        writer.close();

        // Add the file to the Git index, and commit the changes to the local repository
        this.git.add().addFilepattern("temp.txt").call();
        this.git.commit().setMessage("Modified temp.txt").call();

        this.createrepo.setDisable(true);
        status.setText("Successfully initialized the local repository.");
    }

    @FXML
    public void populateAction(ActionEvent actionEvent) throws IOException, GitAPIException, ParseException {

        List<String> dates = new ArrayList<>();
        ObservableList<Calendar> items = listview.getItems();

        for (Calendar date : items) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = formatter.format(LocalDate.ofInstant(date.toInstant(), ZoneId.systemDefault()));
            dates.add(formattedDate);
        }

        for (int i = 0; i < dates.size(); i++) {
            String actualDate = dates.get(i);
            int randomNoOfCommit;
            if(this.randomize.isSelected()){
                randomNoOfCommit = new Random().nextInt(10 - 1 + 1) + 1;
            } else {
                randomNoOfCommit = 1;
            }
            for (int j = 0; j < randomNoOfCommit; j++) {

                FileWriter writer = new FileWriter(this.file, true);
                writer.write(".");
                writer.close();

                // Add the file to the Git index
                this.git.add().addFilepattern("temp.txt").call();

                // Format date of commit
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                Date date = formatter.parse(actualDate);
                LocalDateTime dateTime = LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                ZoneId zone = ZoneId.systemDefault();
                long timestamp = dateTime.atZone(zone).toInstant().toEpochMilli();
                int timezoneOffset = zone.getRules().getOffset(dateTime).getTotalSeconds();
                PersonIdent author_committer = new PersonIdent("Cmh", emailfield.getText(), timestamp, timezoneOffset);

                // Commit the changes to the repository
                this.git.commit().setAuthor(author_committer).setCommitter(author_committer).setMessage("Modified temp.txt").call();

            }
        }
        String token = tokenfield.getText();
        // Push the changes to a remote repository
        this.git.push()
                .setRemote(textfield.getText())
                .setCredentialsProvider(new UsernamePasswordCredentialsProvider(token, ""))
                .call();
        this.populate.setDisable(true);
        status.setText("Successfully pushed changes to remote repository.");
    }


}