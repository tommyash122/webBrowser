package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebHistory;
import javafx.scene.web.WebView;

public class controller implements Initializable{
	@FXML
	private WebView myWebView;
	@FXML
	private TextField myTextField;
	@FXML
	private WebEngine engine;
	@FXML
	private Button myButton;

	private double webZoom;
	private WebHistory history;

	private String homePage;
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		engine = myWebView.getEngine();
		homePage = "www.google.com";
		myTextField.setText(homePage);
		webZoom = 1;
		loadPage();

	}

	public void loadPage()
	{
		//engine.load("http://ww.google.com");
		engine.load("http://" + myTextField.getText());



	}
	public void refreshPage()
	{
		myWebView.setZoom(1);
		engine.reload();
	}

	public void zoomIn()
	{
		webZoom +=0.1;
		myWebView.setZoom(webZoom);
	}
	public void zoomOut()
	{
		webZoom -=0.1;
		myWebView.setZoom(webZoom);
	}
	public void displayHistory()
	{
		history = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = history.getEntries();

		for (WebHistory.Entry entry : entries)
		{
			//System.out.println(entry);
			System.out.println(entry.getUrl() + " " + entry.getLastVisitedDate());
		}
	}

	public void back()
	{
		history = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = history.getEntries();
		history.go(-1);
		myTextField.setText(entries.get(history.getCurrentIndex()).getUrl());

	}
	public void forward()
	{
		history = engine.getHistory();
		ObservableList<WebHistory.Entry> entries = history.getEntries();
		history.go(1);
		myTextField.setText(entries.get(history.getCurrentIndex()).getUrl());
	}

}
