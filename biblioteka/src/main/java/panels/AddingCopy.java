package panels;


import app.Database;
import classes.Book;
import classes.Order;
import lib.BasePanel;
import lib.Settings;

import javax.swing.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Vector;

import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;

public class AddingCopy extends BasePanel {
    private JComboBox<String> chooseLibrary;
    private JComboBox<String> chooseBook;
    private ArrayList<Book> a;

    public AddingCopy() {

        chooseLibrary = new JComboBox<>(Settings.getInstance().database.getLibrariesNames());
        JSplitPane upperSplitPane5 = new JSplitPane();
        upperSplitPane5.setResizeWeight(0.5);
        upperSplitPane5.setOrientation(HORIZONTAL_SPLIT);
        upperSplitPane5.setRightComponent(chooseLibrary);

        upperSplitPane5.setEnabled(false);

        a=Settings.getInstance().database.getBooks("","","","");
        ArrayList<String> b=new ArrayList<String>();
        for (int i=0;i<a.size();i++)
        {
            b.add(a.get(i).getTitle());
        }
        chooseBook= new JComboBox<>(b.toArray(new String[b.size()]));
        upperSplitPane5.setLeftComponent(chooseBook);

        getUpperPanel().add(upperSplitPane5);

        setVisible(true);
    }
    public int ReturnSelectedBookId()
    {
        for (int i=0;i<a.size();i++)
        {
            if (chooseBook.getSelectedItem().equals(a.get(i).getTitle()))
            {
                return a.get(i).getBookID();
            }
        }
        return -1;
    }
    public int ReturnSelectedLibraryId() {
        return  Settings.getInstance().database.getLibraryInfo(chooseLibrary.getSelectedItem().toString()).getLibraryID();
           }
}

