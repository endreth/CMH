module eth.cmh.cmh {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.eclipse.jgit;
    requires jfxtras.controls;

    opens eth.cmh.cmh to javafx.fxml;
    exports eth.cmh.cmh;
}