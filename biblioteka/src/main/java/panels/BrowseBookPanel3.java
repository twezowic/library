package panels;

import app.Database;
import classes.Book;
import lib.BasePanel;
import lib.InteractiveJTextField;
import lib.Settings;
import lombok.Getter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

import static app.Database.getBooks;
import static app.Database.isPenalty;
import static javax.swing.JSplitPane.HORIZONTAL_SPLIT;

@Getter
public class BrowseBookPanel3  extends BasePanel {

    private InteractiveJTextField BookTitle;
    private JTable libraryInfo;
    private DefaultTableModel LibraryInfoTableModel;
    private JTextArea statusInfo;
    private JButton PayButton;
    private JButton SearchButton;

    private JTextArea BookAutor;
    private InteractiveJTextField BookAutorInput;

    private JTextArea BookISBN;
    private InteractiveJTextField BookISBNInput;

    private JTextArea BookGenre;
    private JComboBox<String> BookGenreInput;
    private JTextArea libName;
    private InteractiveJTextField libNameInput;


    public  BrowseBookPanel3(int userId){

        setPreferredSize(Settings.getInstance().BIG_WINDOW_PREFERRED_SIZE);
        setMinimumSize(Settings.getInstance().BIG_WINDOW_MIN_SIZE);
        setLocation(Settings.getInstance().BIG_WINDOW_LOCATION_X, Settings.getInstance().BIG_WINDOW_LOCATION_Y);
        if (isPenalty(userId))
        {
            statusInfo = new JTextArea("you must pay yours penalties");
        }
        else
        {
            statusInfo = new JTextArea("you can make reservation");
        }

        statusInfo.setEditable(false);
        BookTitle = new InteractiveJTextField("Type the name of book you are loking for");
        LibraryInfoTableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        libraryInfo = new JTable(LibraryInfoTableModel);
        BookTitle.setPreferredSize(new Dimension(300, 100));
        statusInfo.setPreferredSize(new Dimension(300, 100));
        libraryInfo.setPreferredSize(new Dimension(500, 500));

        JSplitPane upperSplitPane = new JSplitPane();

        upperSplitPane.setResizeWeight(0.5);
        upperSplitPane.setOrientation(HORIZONTAL_SPLIT);
        upperSplitPane.setLeftComponent(BookTitle);
        upperSplitPane.setRightComponent(statusInfo);
        upperSplitPane.setEnabled(false);
        Dimension minimumSize = new Dimension(100, 100);
        BookTitle.setMinimumSize(minimumSize);
        statusInfo.setMinimumSize(minimumSize);



        BookAutor = new JTextArea("BookAutor:");
        BookAutor.setEditable(false);
        BookAutorInput = new InteractiveJTextField("Type the BookAutor");
        JSplitPane upperSplitPane3 = new JSplitPane();
        upperSplitPane3.setResizeWeight(0.5);
        upperSplitPane3.setOrientation(HORIZONTAL_SPLIT);
        upperSplitPane3.setRightComponent(BookAutorInput);
        upperSplitPane3.setLeftComponent(BookAutor);
        upperSplitPane3.setEnabled(false);
        BookAutorInput.setMinimumSize(minimumSize);
        BookAutor.setMinimumSize(minimumSize);
        getUpperPanel().add(upperSplitPane3);

        BookISBN = new JTextArea("BookISBN:");
        BookISBN.setEditable(false);
        BookISBNInput = new InteractiveJTextField("Type the BookISBN");
        JSplitPane upperSplitPane7 = new JSplitPane();
        upperSplitPane7.setResizeWeight(0.5);
        upperSplitPane7.setOrientation(HORIZONTAL_SPLIT);
        upperSplitPane7.setRightComponent(BookISBNInput);
        upperSplitPane7.setLeftComponent(BookISBN);
        upperSplitPane7.setEnabled(false);
        BookISBNInput.setMinimumSize(minimumSize);
        BookISBN.setMinimumSize(minimumSize);
        getUpperPanel().add(upperSplitPane7);

        BookGenre = new JTextArea("Book Genre:");
        BookGenreInput = new JComboBox<String>(Database.getGenres().toArray(new String[Database.getGenres().size()]));
        BookGenre.setEditable(false);

        JSplitPane upperSplitPane4 = new JSplitPane();
        upperSplitPane4.setResizeWeight(0.5);
        upperSplitPane4.setOrientation(HORIZONTAL_SPLIT);
        upperSplitPane4.setRightComponent(BookGenreInput);
        upperSplitPane4.setLeftComponent(BookGenre);
        upperSplitPane4.setEnabled(false);
        BookGenreInput.setMinimumSize(minimumSize);
        BookGenre.setMinimumSize(minimumSize);
        getUpperPanel().add(upperSplitPane4);

        libName = new JTextArea("library Name:");
        libName.setEditable(false);
        libNameInput = new InteractiveJTextField("Type the library Name");
        JSplitPane upperSplitPane5 = new JSplitPane();
        upperSplitPane5.setResizeWeight(0.5);
        upperSplitPane5.setOrientation(HORIZONTAL_SPLIT);
        upperSplitPane5.setRightComponent(libNameInput);
        upperSplitPane5.setLeftComponent(libName);
        upperSplitPane5.setEnabled(false);
        libNameInput.setMinimumSize(minimumSize);
        libName.setMinimumSize(minimumSize);
        getUpperPanel().add(upperSplitPane5);





        getAcceptButton().setText("reserv");
        if (userId==0)
        {
            getAcceptButton().setVisible(false);
        }
        getUpperPanel().setLayout(new BoxLayout(getUpperPanel(),BoxLayout.Y_AXIS));
        getUpperPanel().add(upperSplitPane);
        getUpperPanel().add(libraryInfo);
        PayButton= new JButton("Pay");
        PayButton.setVisible(false);
        PayButton.setMinimumSize(minimumSize);
        if (isPenalty(userId))
        {
         PayButton.setVisible(true);

        }
        SearchButton= new JButton("search");
        SearchButton.setMinimumSize(minimumSize);
        JSplitPane upperSplitPane6 = new JSplitPane();
        upperSplitPane6.setResizeWeight(0.5);
        upperSplitPane6.setOrientation(HORIZONTAL_SPLIT);
        upperSplitPane6.setRightComponent(PayButton);
        upperSplitPane6.setLeftComponent(SearchButton);
        upperSplitPane6.setEnabled(false);
        getUpperPanel().add(upperSplitPane6);
        setVisible(true);
        getLibraryInfoTableModel().setColumnCount(7);

    }
    public void fillLibraryInfo()
    {   String title = BookTitle.getText();
        if (title.equals("Type the name of book you are loking for"))
        {
            title="";
        }

        String Autor = BookAutorInput.getText();
        if (Autor.equals("Type the BookAutor"))
        {
            Autor="";
        }
        String ISBN = BookISBNInput.getText();
        if (ISBN.equals("Type the BookISBN"))
        {
            ISBN="";
        }
        String Genre = BookGenreInput.getSelectedItem().toString();
        if (Genre.equals("Type the Book Genre"))
        {
            Genre="";
        }
        String libname = libNameInput.getText();
        if (libname.equals("Type the library Name"))
        {
            libname="Biblioteka Miejska";
        }
        ArrayList<Book> a= getBooks(title,Autor, ISBN, Genre);
        getLibraryInfoTableModel().setRowCount(0);
        for (int i=0;i<a.size();i++)
        {

            getLibraryInfoTableModel().addRow(new Object[] {a.get(i).getTitle(),a.get(i).getAuthor(),a.get(i).getPages(),
                    a.get(i).getISBN(),a.get(i).getYear(),a.get(i).getGenre(),a.get(i).getBookID()
            });
        }

        }

    public void reserv(int userId)
    {try
        {
 //           statusInfo.setText("you successful reserv "+libraryInfo.getValueAt(libraryInfo.getSelectedRow(),0));
        Database.orderBook(userId,Database.getAvailableCopies(Integer.valueOf(libraryInfo.getValueAt(libraryInfo.getSelectedRow(),6).toString())).get(0).getCopyID());

statusInfo.setText("you successful reserv "+libraryInfo.getValueAt(libraryInfo.getSelectedRow(),0));
        }
    catch (Exception e){
        // will use the default Look and Feel instead
        int a=4;
    }
    }
    }


