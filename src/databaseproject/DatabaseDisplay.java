/*TODO:
 *  Login System/Supprot for users
 *      Delete Cards(Require authentication, even if user is alreay logged in)
 *      Email export list
 *      Create user
 *      Label for current user
 *  Migrate to my database
 *      Maybe require authentication for connection?
 *  Add sets
 *      Add cards to sets(only require names)
 *  Refine import system
 *      Formatting check
 */
package databaseproject;

import java.awt.FileDialog;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DatabaseDisplay extends javax.swing.JFrame {

    // These hold the items in the search/price lists
    public DefaultListModel<String> data = new DefaultListModel<>();
    public DefaultListModel<String> searchData = new DefaultListModel<>();
    //These are the selected indices of the search/price lists
    public ArrayList<Integer> selected = new ArrayList<>();
    public ArrayList<Integer> searchSelected = new ArrayList<>();
    //Tuples holding the card names and prices of cards in the lists. Used to reduce the number of queries run
    public ArrayList<MTGDBResult> resultCardList = new ArrayList<>();
    public ArrayList<MTGDBResult> priceCardList = new ArrayList<>();
    //Mysql connection variables
    public Connection conn = null;
    public Statement stmt;
    public ResultSet rs;
    //Variables for the price label
    public String RunningTotalLabelString = "Total Price: ";
    double totalPrice = 0.0;
    //Bottom TextField stuff(I'm testing this)
    String first = "Query to run: ";
    String searchQuery = "Select Name,Price FROM Cards";
    String addQuery = "INSERT INTO Cards (id,name,type,supertype,subtype,color,cost,rarity,abilities,power,toughness,price) VALUES (0,";
    String home = "Select Name,Price FROM Cards";

    //Error Dialogue Box
    public DatabaseDisplay() {
        initComponents();
        /*Try to connect to the database*/
        try {
            Class.forName("com.mysql.jdbc.Driver");
            JTextField dbLocation = new JTextField();
            JTextField dbUsername = new JTextField();
            JTextField dbPassword = new JTextField();
            String location = "";
            String user = "";
            String pass = "";
            Object[] message = {
                "Database location: ", dbLocation,
                "Username: ", dbUsername,
                "Password: ", dbPassword
            };
            int option = JOptionPane.showConfirmDialog(this, message, "Enter database information", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    location = dbLocation.getText();
                    user = dbUsername.getText();
                    pass = dbPassword.getText();
                }//end if
            conn = DriverManager.getConnection(location, user, pass);
            /*If no errors, you are connected to the database*/

            stmt = conn.createStatement();
            stmt.executeQuery("Use mtgdb813");
            rs = stmt.executeQuery("SELECT Name, Price FROM Cards");
            while (rs.next()) {
                /*This loop adds all the cards in the database to the searched 
                 * list, as well as their name/price tuples to the appropriate list
                 */
                searchData.addElement(rs.getString("Name"));
                resultCardList.add(new MTGDBResult(rs.getString("Name"), rs.getDouble("Price")));
            }//end while
            SearchSetComboBox.addItem("");
            /*This loop gets the lsit of set names, and adds them, after an 
             * empty space, to the Set ComboBox
             */
            rs = stmt.executeQuery("SELECT setName FROM Sets");
            while (rs.next()) {
                SearchSetComboBox.addItem(rs.getString("setName"));
            }//end while
        } catch (SQLException ex) {
            showError(ex.toString(), "SQL Error");
        } catch (ClassNotFoundException ex) {
            showError(ex.toString(), "Class Error");
        }//end try catch block
        RunningTotalLabel.setText(RunningTotalLabelString + totalPrice);
        QueryDisplay.setText(first + searchQuery);
    }//end constructor    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TabbedPane = new javax.swing.JTabbedPane();
        SearchTabPanel = new javax.swing.JPanel();
        SearchScrollPane = new javax.swing.JScrollPane();
        SearchCardList = new javax.swing.JList();
        SearchLabel = new javax.swing.JLabel();
        SearchNameLabel = new javax.swing.JLabel();
        SearchNameTextField = new javax.swing.JTextField();
        AddSearchSelectedButton = new javax.swing.JButton();
        AddSearchedButton = new javax.swing.JButton();
        SearchGreenCheckBox = new javax.swing.JCheckBox();
        SearchWhiteCheckBox = new javax.swing.JCheckBox();
        SearchBlueCheckBox = new javax.swing.JCheckBox();
        SearchBlackCheckBox = new javax.swing.JCheckBox();
        SearchRedCheckBox = new javax.swing.JCheckBox();
        SearchCardColorLabel = new javax.swing.JLabel();
        SearchButton = new javax.swing.JButton();
        SearchExactColorCheckbox = new javax.swing.JCheckBox();
        SearchExcludeColorsCheckbox = new javax.swing.JCheckBox();
        SearchCostLabel = new javax.swing.JLabel();
        SearchCostTextField = new javax.swing.JTextField();
        SearchSupertypeLabel = new javax.swing.JLabel();
        SearchSupertypeComboBox = new javax.swing.JComboBox();
        SearchTypeLabel = new javax.swing.JLabel();
        SearchTypeComboBox = new javax.swing.JComboBox();
        SearchSubtypeLabel = new javax.swing.JLabel();
        SearchSubtypeTextField = new javax.swing.JTextField();
        SearchRarityLabel = new javax.swing.JLabel();
        SearchRarityComboBox = new javax.swing.JComboBox();
        SearchPriceLabel = new javax.swing.JLabel();
        SearchPriceTextField = new javax.swing.JTextField();
        SearchPriceModifierComboBox = new javax.swing.JComboBox();
        SearchPowerLabel = new javax.swing.JLabel();
        SearchPowerTextField = new javax.swing.JTextField();
        SearchToughnessLabel = new javax.swing.JLabel();
        SearchToughnessTextField = new javax.swing.JTextField();
        SearchAbilitiesLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        SearchAbilitiesTextArea = new javax.swing.JTextArea();
        SearchSetLabel = new javax.swing.JLabel();
        SearchSetComboBox = new javax.swing.JComboBox();
        RefreshButton = new javax.swing.JButton();
        BannedRestricedLabel = new javax.swing.JLabel();
        BannedRestrictedComboBox = new javax.swing.JComboBox();
        AddCardTabPanel = new javax.swing.JPanel();
        AddACardLabel = new javax.swing.JLabel();
        AddCardNameLabel = new javax.swing.JLabel();
        AddCardNameTextField = new javax.swing.JTextField();
        AddColorLabel = new javax.swing.JLabel();
        AddWhiteCheckBox = new javax.swing.JCheckBox();
        AddBlueCheckBox = new javax.swing.JCheckBox();
        AddBlackCheckBox = new javax.swing.JCheckBox();
        AddRedCheckBox = new javax.swing.JCheckBox();
        AddGreenCheckBox = new javax.swing.JCheckBox();
        AddSuperTypeLabel = new javax.swing.JLabel();
        AddSupertypeComboBox = new javax.swing.JComboBox();
        AddTypeLabel = new javax.swing.JLabel();
        AddTypeComboBox = new javax.swing.JComboBox();
        AddSubtypeLabel = new javax.swing.JLabel();
        AddSubtypeTextField = new javax.swing.JTextField();
        AddCostLabel = new javax.swing.JLabel();
        AddCostTextField = new javax.swing.JTextField();
        AddRarityLabel = new javax.swing.JLabel();
        AddRarityComboBox = new javax.swing.JComboBox();
        AddPowerLabel = new javax.swing.JLabel();
        AddPowerTextField = new javax.swing.JTextField();
        AddToughnessLabel = new javax.swing.JLabel();
        AddToughnessTextField = new javax.swing.JTextField();
        AddAbilitiesLabel = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        AddAbilitiesTextArea = new javax.swing.JTextArea();
        AddPriceLabel = new javax.swing.JLabel();
        AddPriceTextField = new javax.swing.JTextField();
        AddCardButton = new javax.swing.JButton();
        RunningTotalScrollPane = new javax.swing.JScrollPane();
        SelectedCardList = new javax.swing.JList();
        RunningTotalLabel = new javax.swing.JLabel();
        RemoveSelectedButton = new javax.swing.JButton();
        RemoveAllButton = new javax.swing.JButton();
        QueryDisplay = new javax.swing.JTextField();
        MenuBar = new javax.swing.JMenuBar();
        FileMenuButton = new javax.swing.JMenu();
        ImportListMenuButton = new javax.swing.JMenuItem();
        ExportListMenuButton = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("MTG Inventory");

        TabbedPane.setToolTipText("");
        TabbedPane.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                TabbedPaneStateChanged(evt);
            }
        });

        SearchTabPanel.setToolTipText("");

        SearchCardList.setModel(searchData);
        SearchCardList.setToolTipText("Cards you searched for");
        SearchCardList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AddToTotalList(evt);
            }
        });
        SearchScrollPane.setViewportView(SearchCardList);

        SearchLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        SearchLabel.setText("Search for a card:");

        SearchNameLabel.setText("Card Name:");
        SearchNameLabel.setToolTipText("Search by name");

        SearchNameTextField.setToolTipText("Search by name");
        SearchNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchNameTextFieldActionPerformed(evt);
            }
        });
        SearchNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchNameTextFieldKeyReleased(evt);
            }
        });

        AddSearchSelectedButton.setText("Add Selected to Price List");
        AddSearchSelectedButton.setToolTipText("Add selected cards to the price list");
        AddSearchSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddSearchSelectedButtonActionPerformed(evt);
            }
        });

        AddSearchedButton.setText("Add All to Price List");
        AddSearchedButton.setToolTipText("Add all found cards to the price list");
        AddSearchedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddSearchedButtonActionPerformed(evt);
            }
        });

        SearchGreenCheckBox.setText("Green");
        SearchGreenCheckBox.setToolTipText("Search for green cards");
        SearchGreenCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchGreenCheckBoxActionPerformed(evt);
            }
        });

        SearchWhiteCheckBox.setText("White");
        SearchWhiteCheckBox.setToolTipText("Search for white cards");
        SearchWhiteCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchWhiteCheckBoxActionPerformed(evt);
            }
        });

        SearchBlueCheckBox.setText("Blue");
        SearchBlueCheckBox.setToolTipText("Search for blue cards");
        SearchBlueCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBlueCheckBoxActionPerformed(evt);
            }
        });

        SearchBlackCheckBox.setText("Black");
        SearchBlackCheckBox.setToolTipText("Search for black cards");
        SearchBlackCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchBlackCheckBoxActionPerformed(evt);
            }
        });

        SearchRedCheckBox.setText("Red");
        SearchRedCheckBox.setToolTipText("Search for red cards");
        SearchRedCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchRedCheckBoxActionPerformed(evt);
            }
        });

        SearchCardColorLabel.setText("Color:");

        SearchButton.setText("Search");
        SearchButton.setToolTipText("Search for cards");
        SearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchButtonActionPerformed(evt);
            }
        });

        SearchExactColorCheckbox.setText("Match Colors Exactly('and' instead of 'or')");
        SearchExactColorCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchExactColorCheckboxActionPerformed(evt);
            }
        });

        SearchExcludeColorsCheckbox.setText("Exclude Unselected Colors");
        SearchExcludeColorsCheckbox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchExcludeColorsCheckboxActionPerformed(evt);
            }
        });

        SearchCostLabel.setText("Cost:");

        SearchCostTextField.setToolTipText("Search by card converted mana cost");
        SearchCostTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchCostTextFieldActionPerformed(evt);
            }
        });
        SearchCostTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchCostTextFieldKeyReleased(evt);
            }
        });

        SearchSupertypeLabel.setText("Supertype:");

        SearchSupertypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Basic", "Elite", "Legendary","Ongoing","Snow","World"}));
        SearchSupertypeComboBox.setToolTipText("Select card supertype to search");
        SearchSupertypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchSupertypeComboBoxActionPerformed(evt);
            }
        });

        SearchTypeLabel.setText("Type:");

        SearchTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Artifact", "Creature", "Enchantment", "Hero", "Instant", "Land", "Phenomenon", "Plane", "Planeswalker", "Scheme", "Sorcery", "Tribal", "Vangaurd" }));
        SearchTypeComboBox.setToolTipText("Search by card type");
        SearchTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchTypeComboBoxActionPerformed(evt);
            }
        });

        SearchSubtypeLabel.setText("Subtype:");

        SearchSubtypeTextField.setToolTipText("Search by card subtype");
        SearchSubtypeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchSubtypeTextFieldActionPerformed(evt);
            }
        });
        SearchSubtypeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchSubtypeTextFieldKeyReleased(evt);
            }
        });

        SearchRarityLabel.setText("Rarity:");

        SearchRarityComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Common", "Uncommon", "Rare", "Mythic", "Timeshifted" }));
        SearchRarityComboBox.setToolTipText("Search by card rarity");
        SearchRarityComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchRarityComboBoxActionPerformed(evt);
            }
        });

        SearchPriceLabel.setText("Price:");

        SearchPriceTextField.setToolTipText("Search by card price");
        SearchPriceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchPriceTextFieldActionPerformed(evt);
            }
        });
        SearchPriceTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchPriceTextFieldKeyReleased(evt);
            }
        });

        SearchPriceModifierComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "=", "!=", "<", ">", "<=", ">=" }));
        SearchPriceModifierComboBox.setToolTipText("Cost comparison signs");
        SearchPriceModifierComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchPriceModifierComboBoxActionPerformed(evt);
            }
        });

        SearchPowerLabel.setText("Power:");

        SearchPowerTextField.setToolTipText("Search by card power");
        SearchPowerTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchPowerTextFieldActionPerformed(evt);
            }
        });
        SearchPowerTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchPowerTextFieldKeyReleased(evt);
            }
        });

        SearchToughnessLabel.setText("Toughness:");

        SearchToughnessTextField.setToolTipText("Search by card toughness");
        SearchToughnessTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchToughnessTextFieldActionPerformed(evt);
            }
        });
        SearchToughnessTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchToughnessTextFieldKeyReleased(evt);
            }
        });

        SearchAbilitiesLabel.setText("Abilities:");

        SearchAbilitiesTextArea.setColumns(20);
        SearchAbilitiesTextArea.setRows(5);
        SearchAbilitiesTextArea.setToolTipText("Search by card abilities");
        SearchAbilitiesTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                SearchAbilitiesTextAreaKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(SearchAbilitiesTextArea);

        SearchSetLabel.setText("Set:");

        SearchSetComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));
        SearchSetComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchSetComboBoxActionPerformed(evt);
            }
        });

        RefreshButton.setText("Refresh List");
        RefreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RefreshButtonActionPerformed(evt);
            }
        });

        BannedRestricedLabel.setText("Banned/Restricted:");

        BannedRestrictedComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Banned as a Commander", "Banned in Commander", "Allowed as a Commander", "Allowed in Commander", "Banned in Vintage" , "Restricted in Vintage" , "Allowed in Vintage" , "Banned in Legacy" , "Allowed in Legacy" , "Banned in Modern" , "Banned in Standard" }));
        BannedRestrictedComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BannedRestrictedComboBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SearchTabPanelLayout = new javax.swing.GroupLayout(SearchTabPanel);
        SearchTabPanel.setLayout(SearchTabPanelLayout);
        SearchTabPanelLayout.setHorizontalGroup(
            SearchTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchTabPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(SearchTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(SearchGreenCheckBox)
                    .addGroup(SearchTabPanelLayout.createSequentialGroup()
                        .addComponent(SearchSupertypeLabel)
                        .addGap(3, 3, 3)
                        .addComponent(SearchSupertypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchTypeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(SearchLabel)
                    .addGroup(SearchTabPanelLayout.createSequentialGroup()
                        .addGroup(SearchTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(SearchCardColorLabel)
                            .addComponent(SearchNameLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchSetLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchSetComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(SearchRedCheckBox)
                    .addGroup(SearchTabPanelLayout.createSequentialGroup()
                        .addComponent(SearchPowerLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchPowerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchToughnessLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchToughnessTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SearchTabPanelLayout.createSequentialGroup()
                        .addComponent(SearchCostLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchCostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchRarityLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchRarityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(SearchTabPanelLayout.createSequentialGroup()
                        .addComponent(SearchSubtypeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchSubtypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(SearchAbilitiesLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SearchTabPanelLayout.createSequentialGroup()
                        .addComponent(SearchPriceLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchPriceModifierComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(SearchButton)
                    .addGroup(SearchTabPanelLayout.createSequentialGroup()
                        .addGroup(SearchTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SearchTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, SearchTabPanelLayout.createSequentialGroup()
                                    .addComponent(SearchWhiteCheckBox)
                                    .addGap(18, 18, 18))
                                .addGroup(SearchTabPanelLayout.createSequentialGroup()
                                    .addComponent(SearchBlueCheckBox)
                                    .addGap(26, 26, 26)))
                            .addGroup(SearchTabPanelLayout.createSequentialGroup()
                                .addComponent(SearchBlackCheckBox)
                                .addGap(22, 22, 22)))
                        .addGroup(SearchTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SearchTabPanelLayout.createSequentialGroup()
                                .addComponent(BannedRestricedLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BannedRestrictedComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(SearchExactColorCheckbox)
                            .addComponent(SearchExcludeColorsCheckbox))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 370, Short.MAX_VALUE)
                .addGroup(SearchTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AddSearchedButton)
                    .addComponent(SearchScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddSearchSelectedButton)
                    .addComponent(RefreshButton)))
        );
        SearchTabPanelLayout.setVerticalGroup(
            SearchTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SearchTabPanelLayout.createSequentialGroup()
                .addGroup(SearchTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(SearchTabPanelLayout.createSequentialGroup()
                        .addComponent(SearchLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(SearchTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SearchNameLabel)
                            .addComponent(SearchNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SearchSetLabel)
                            .addComponent(SearchSetComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchCardColorLabel)
                        .addGap(5, 5, 5)
                        .addGroup(SearchTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SearchWhiteCheckBox)
                            .addComponent(SearchExactColorCheckbox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(SearchTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SearchBlueCheckBox)
                            .addComponent(SearchExcludeColorsCheckbox))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(SearchTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SearchBlackCheckBox)
                            .addComponent(BannedRestricedLabel)
                            .addComponent(BannedRestrictedComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchRedCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchGreenCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(SearchTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SearchSupertypeLabel)
                            .addComponent(SearchSupertypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SearchTypeLabel)
                            .addComponent(SearchTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(SearchTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(SearchTabPanelLayout.createSequentialGroup()
                                .addComponent(SearchSubtypeLabel)
                                .addGap(7, 7, 7)
                                .addGroup(SearchTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(SearchRarityLabel)
                                    .addComponent(SearchRarityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SearchCostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SearchCostLabel))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(SearchTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(SearchPowerLabel)
                                    .addComponent(SearchPowerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(SearchToughnessLabel)
                                    .addComponent(SearchToughnessTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(SearchSubtypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchAbilitiesLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(SearchTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(SearchPriceLabel)
                            .addComponent(SearchPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(SearchPriceModifierComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(SearchButton))
                    .addGroup(SearchTabPanelLayout.createSequentialGroup()
                        .addComponent(SearchScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddSearchedButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddSearchSelectedButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RefreshButton)))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Search", SearchTabPanel);

        AddACardLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        AddACardLabel.setText("Add a card to the database:");

        AddCardNameLabel.setText("Card Name:");

        AddCardNameTextField.setToolTipText("Name of the card to put into the database");
        AddCardNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCardNameTextFieldActionPerformed(evt);
            }
        });
        AddCardNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AddCardNameTextFieldKeyReleased(evt);
            }
        });

        AddColorLabel.setText("Colors:");

        AddWhiteCheckBox.setText("White");
        AddWhiteCheckBox.setToolTipText("Card is white colored");
        AddWhiteCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddWhiteCheckBoxActionPerformed(evt);
            }
        });

        AddBlueCheckBox.setText("Blue");
        AddBlueCheckBox.setToolTipText("Card is blue colored");
        AddBlueCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBlueCheckBoxActionPerformed(evt);
            }
        });

        AddBlackCheckBox.setText("Black");
        AddBlackCheckBox.setToolTipText("Card is black colored");
        AddBlackCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddBlackCheckBoxActionPerformed(evt);
            }
        });

        AddRedCheckBox.setText("Red");
        AddRedCheckBox.setToolTipText("Card is red colored");
        AddRedCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddRedCheckBoxActionPerformed(evt);
            }
        });

        AddGreenCheckBox.setText("Green");
        AddGreenCheckBox.setToolTipText("Card is green colored");
        AddGreenCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddGreenCheckBoxActionPerformed(evt);
            }
        });

        AddSuperTypeLabel.setText("Supertype:");

        AddSupertypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "", "Basic", "Elite", "Legendary","Ongoing","Snow","World"}));
        AddSupertypeComboBox.setToolTipText("Supertype of the card");
        AddSupertypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddSupertypeComboBoxActionPerformed(evt);
            }
        });

        AddTypeLabel.setText("Type:");

        AddTypeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"", "Artifact", "Creature", "Enchantment", "Hero", "Instant", "Land", "Phenomenon", "Plane", "Planeswalker", "Scheme", "Sorcery", "Tribal", "Vangaurd"}));
        AddTypeComboBox.setToolTipText("Type of the card");
        AddTypeComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddTypeComboBoxActionPerformed(evt);
            }
        });

        AddSubtypeLabel.setText("Subtype:");

        AddSubtypeTextField.setToolTipText("Subtype of the card");
        AddSubtypeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddSubtypeTextFieldActionPerformed(evt);
            }
        });
        AddSubtypeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AddSubtypeTextFieldKeyReleased(evt);
            }
        });

        AddCostLabel.setText("Cost:");

        AddCostTextField.setToolTipText("Cost of the card");
        AddCostTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCostTextFieldActionPerformed(evt);
            }
        });
        AddCostTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AddCostTextFieldKeyReleased(evt);
            }
        });

        AddRarityLabel.setText("Rarity:");

        AddRarityComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Common", "Uncommon", "Rare", "Mythic", "Timeshifted" }));
        AddRarityComboBox.setToolTipText("Rarity of the card");
        AddRarityComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddRarityComboBoxActionPerformed(evt);
            }
        });

        AddPowerLabel.setText("Power:");

        AddPowerTextField.setToolTipText("Power of the card");
        AddPowerTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPowerTextFieldActionPerformed(evt);
            }
        });
        AddPowerTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AddPowerTextFieldKeyReleased(evt);
            }
        });

        AddToughnessLabel.setText("Toughness:");

        AddToughnessTextField.setToolTipText("Toughness of the card");
        AddToughnessTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToughnessTextFieldActionPerformed(evt);
            }
        });
        AddToughnessTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AddToughnessTextFieldKeyReleased(evt);
            }
        });

        AddAbilitiesLabel.setText("Abilities:");

        AddAbilitiesTextArea.setColumns(20);
        AddAbilitiesTextArea.setRows(5);
        AddAbilitiesTextArea.setToolTipText("Abilities text of the card");
        AddAbilitiesTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AddAbilitiesTextAreaKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(AddAbilitiesTextArea);

        AddPriceLabel.setText("Price:");

        AddPriceTextField.setToolTipText("Price of the card");
        AddPriceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddPriceTextFieldActionPerformed(evt);
            }
        });
        AddPriceTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                AddPriceTextFieldKeyReleased(evt);
            }
        });

        AddCardButton.setText("Add to database");
        AddCardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddCardButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AddCardTabPanelLayout = new javax.swing.GroupLayout(AddCardTabPanel);
        AddCardTabPanel.setLayout(AddCardTabPanelLayout);
        AddCardTabPanelLayout.setHorizontalGroup(
            AddCardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddCardTabPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AddCardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddCardTabPanelLayout.createSequentialGroup()
                        .addGroup(AddCardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AddGreenCheckBox)
                            .addGroup(AddCardTabPanelLayout.createSequentialGroup()
                                .addComponent(AddCostLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddCostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddRarityLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddRarityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(AddCardTabPanelLayout.createSequentialGroup()
                        .addGroup(AddCardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(AddACardLabel)
                            .addComponent(AddRedCheckBox)
                            .addComponent(AddBlackCheckBox)
                            .addGroup(AddCardTabPanelLayout.createSequentialGroup()
                                .addGroup(AddCardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AddCardNameLabel)
                                    .addComponent(AddColorLabel)
                                    .addComponent(AddWhiteCheckBox))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(AddCardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(AddCardNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(AddCardTabPanelLayout.createSequentialGroup()
                                        .addGroup(AddCardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(AddSuperTypeLabel)
                                            .addComponent(AddTypeLabel)
                                            .addComponent(AddSubtypeLabel))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(AddCardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(AddSubtypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(AddTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(AddSupertypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addComponent(AddBlueCheckBox)
                            .addGroup(AddCardTabPanelLayout.createSequentialGroup()
                                .addComponent(AddPowerLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddPowerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddToughnessLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddToughnessTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(AddAbilitiesLabel)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(AddCardTabPanelLayout.createSequentialGroup()
                                .addComponent(AddPriceLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(AddPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(AddCardButton))
                        .addContainerGap(596, Short.MAX_VALUE))))
        );
        AddCardTabPanelLayout.setVerticalGroup(
            AddCardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AddCardTabPanelLayout.createSequentialGroup()
                .addComponent(AddACardLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddCardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddCardNameLabel)
                    .addComponent(AddCardNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddCardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddColorLabel)
                    .addComponent(AddSuperTypeLabel)
                    .addComponent(AddSupertypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddCardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AddCardTabPanelLayout.createSequentialGroup()
                        .addComponent(AddWhiteCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddBlueCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddBlackCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddRedCheckBox)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AddGreenCheckBox))
                    .addGroup(AddCardTabPanelLayout.createSequentialGroup()
                        .addGroup(AddCardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddTypeLabel)
                            .addComponent(AddTypeComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(AddCardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(AddSubtypeLabel)
                            .addComponent(AddSubtypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddCardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddCostLabel)
                    .addComponent(AddCostTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddRarityLabel)
                    .addComponent(AddRarityComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddCardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddPowerLabel)
                    .addComponent(AddPowerTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(AddToughnessLabel)
                    .addComponent(AddToughnessTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddAbilitiesLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AddCardTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(AddPriceLabel)
                    .addComponent(AddPriceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AddCardButton)
                .addGap(0, 61, Short.MAX_VALUE))
        );

        TabbedPane.addTab("Add Card", AddCardTabPanel);

        SelectedCardList.setModel(data);
        SelectedCardList.setToolTipText("Cards in your price list");
        SelectedCardList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SelectedCardListMouseClicked(evt);
            }
        });
        RunningTotalScrollPane.setViewportView(SelectedCardList);

        RunningTotalLabel.setText("Total Price: ");

        RemoveSelectedButton.setText("Remove Selected");
        RemoveSelectedButton.setToolTipText("Remove the cards you have selected");
        RemoveSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveSelectedButtonActionPerformed(evt);
            }
        });

        RemoveAllButton.setText("Remove All");
        RemoveAllButton.setToolTipText("Remove all cards in the list");
        RemoveAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RemoveAllButtonActionPerformed(evt);
            }
        });

        QueryDisplay.setText("Query to be run:");
        QueryDisplay.setToolTipText("Displays the query that is built");
        QueryDisplay.setDisabledTextColor(new java.awt.Color(255, 255, 255));
        QueryDisplay.setDoubleBuffered(true);

        FileMenuButton.setText("File");

        ImportListMenuButton.setText("Import a list of cards to the database");
        ImportListMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ImportListMenuButtonActionPerformed(evt);
            }
        });
        FileMenuButton.add(ImportListMenuButton);

        ExportListMenuButton.setText("Export selected cards to .csv");
        ExportListMenuButton.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ExportListMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExportListMenuButtonActionPerformed(evt);
            }
        });
        FileMenuButton.add(ExportListMenuButton);

        MenuBar.add(FileMenuButton);

        setJMenuBar(MenuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RunningTotalScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(RunningTotalLabel)
                    .addComponent(RemoveSelectedButton)
                    .addComponent(RemoveAllButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TabbedPane))
            .addComponent(QueryDisplay)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(RunningTotalScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 322, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(RunningTotalLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RemoveSelectedButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(RemoveAllButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(TabbedPane))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(QueryDisplay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        TabbedPane.setToolTipTextAt(0, "Search for a card");
        TabbedPane.setToolTipTextAt(1, "Add a card to the database");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RemoveAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveAllButtonActionPerformed
        //This function removes all the cards from the price list, and decreases the price accordingly
        String query = "SELECT Price FROM Cards WHERE Name = '";
        for (int i = data.size() - 1; i >= 0; i--) {
            totalPrice -= priceCardList.get(i).cardPrice;
            priceCardList.remove(i);
        }//end for
        RunningTotalLabel.setText(RunningTotalLabelString + totalPrice);
        data.removeAllElements();
    }//GEN-LAST:event_RemoveAllButtonActionPerformed

    private void SelectedCardListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SelectedCardListMouseClicked
        //This handles mouse clicks to the price list
        selected.clear();
        //If single-click, add the selected index to the array of selected indices
        if (evt.getClickCount() == 1) {
            int[] selectedIx = this.SelectedCardList.getSelectedIndices();
            for (int i = 0; i < selectedIx.length; i++) {
                selected.add(selectedIx[i]);
            }//end for
        }//end if
        //If double-click, remove the clicked item from list, and decrease price
        else if (evt.getClickCount() == 2) {
            int i = SelectedCardList.getSelectedIndex();
            totalPrice -= priceCardList.get(i).cardPrice;
            priceCardList.remove(i);
            RunningTotalLabel.setText(RunningTotalLabelString + totalPrice);
            data.removeElementAt(SelectedCardList.getSelectedIndex());
        }//end else if
    }//GEN-LAST:event_SelectedCardListMouseClicked

    private void RemoveSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RemoveSelectedButtonActionPerformed
        //This removes all selected items from the price list, and decreases the price
        for (int i = 0; i < selected.size(); i++) {
            totalPrice -= priceCardList.get(i).cardPrice;
            priceCardList.remove(i);
            RunningTotalLabel.setText(RunningTotalLabelString + totalPrice);
            data.removeElementAt(selected.get(i));
            for (int j = i + 1; j < selected.size(); j++) {
                selected.set(j, selected.get(j) - 1);
            }//end for
        }//end for
        SelectedCardList.clearSelection();
        selected.clear();
    }//GEN-LAST:event_RemoveSelectedButtonActionPerformed

    private void ImportListMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ImportListMenuButtonActionPerformed
        //This function imports a list of cards from a .csv file. It works, but it's not ideal

        //These variables set up the dialog to choose the file to load
        FileDialog fd = new FileDialog(this, "Choose a file", FileDialog.LOAD);
        fd.setDirectory("C:\\");
        fd.setVisible(true);

        //These two get the filename and the path to the file
        String filename = fd.getFile();
        String path = fd.getDirectory();

        //Variables for reading the .csv
        BufferedReader br;
        String line;
        String[] csvSeparator;

        //Query building variables
        String query = "INSERT INTO Cards(id,name,type,subtype,supertype,color,cost,rarity,abilities,power,toughness,price) VALUES (0,";
        String queryFinisher;

        //Only try to import if a filename has been inputted, and is a .csv
        if (filename != null && filename.contains(".csv")) {
            try {
                br = new BufferedReader(new FileReader(path + filename));
                System.out.println(path + filename);
                //Consume the first line, which contains column info
                br.readLine();
                //Run through the file until done
                line = br.readLine();
                while (line != null) {
                    csvSeparator = line.split(",");
                    /*Build the variable part of the query. 
                     * ~ has been used as a replacement for commas, because commas
                     * cause problems when reading from .csv files
                     */
                    for (String string : csvSeparator) {
                        System.out.println(string);
                    }
                    queryFinisher = "'" + csvSeparator[1].replaceAll("~", ",").replaceAll("'", "''") + "',"
                            + "'" + csvSeparator[2].replaceAll("~", ",").replaceAll("'", "''") + "',"
                            + "'" + csvSeparator[3].replaceAll("~", ",").replaceAll("'", "''") + "',"
                            + "'" + csvSeparator[4].replaceAll("~", ",").replaceAll("'", "''") + "',"
                            + "'" + csvSeparator[5].replaceAll("~", ",").replaceAll("'", "''") + "',"
                            + "'" + csvSeparator[6].replaceAll("~", ",").replaceAll("'", "''") + "',"
                            + "'" + csvSeparator[7].replaceAll("~", ",").replaceAll("'", "''") + "',"
                            + "'" + csvSeparator[8].replaceAll("~", ",").replaceAll("'", "''") + "',"
                            + csvSeparator[9].replaceAll("'", "''") + ","
                            + csvSeparator[10].replaceAll("'", "''") + ","
                            + csvSeparator[11].replaceAll("'", "''") + ")";
                    String tempQuery = query + queryFinisher.replaceAll("\"", "");
                    stmt.executeUpdate(tempQuery);
                    line = br.readLine();
                }//end while
                JOptionPane.showMessageDialog(null, "Import Complete!");
            } catch (FileNotFoundException ex) {
                showError(ex.toString(), "File Error");
            } catch (IOException ex) {
                showError(ex.toString(), "File Error");
            } catch (SQLException ex) {
                showError(ex.toString(), "SQL Error");
            }//end try catch block
        }//end if
    }//GEN-LAST:event_ImportListMenuButtonActionPerformed

    private void ExportListMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExportListMenuButtonActionPerformed
        //Export the price list to a .csv file

        //Only do this if there are items in the price list
        if (!data.isEmpty()) {

            //Same as above, dialog variables
            FileDialog fd = new FileDialog(this, "Choose a file", FileDialog.SAVE);
            fd.setDirectory("C:\\");
            fd.setVisible(true);

            //Again, file name/path
            String filename = fd.getFile();
            String path = fd.getDirectory();

            //Abilities variable, because it can be null
            String abilities;

            //If a file name has been entered, go ahead and export
            if (filename != null) {
                //If the user did not specify the .csv extension, append it to the file name
                if (!filename.contains(".csv")) {
                    filename += ".csv";
                }//end if
                try {
                    //Declare and initialize the FileWriter
                    FileWriter writer;
                    writer = new FileWriter(path + filename);
                    //Set up the column information
                    writer.append("Id");
                    writer.append(",");
                    writer.append("Name");
                    writer.append(",");
                    writer.append("Type");
                    writer.append(",");
                    writer.append("Subtype");
                    writer.append(",");
                    writer.append("Supertype");
                    writer.append(",");
                    writer.append("Color");
                    writer.append(",");
                    writer.append("Cost");
                    writer.append(",");
                    writer.append("Rarity");
                    writer.append(",");
                    writer.append("Abilities");
                    writer.append(",");
                    writer.append("Power");
                    writer.append(",");
                    writer.append("Toughness");
                    writer.append(",");
                    writer.append("Price");
                    writer.append("\n");
                    /*This loop runs through the price list, queries information
                     * from the database by name, and writes that information to
                     * the specified .csv file.
                     */
                    for (int i = 0; i < priceCardList.size(); i++) {
                        rs = stmt.executeQuery("SELECT * FROM Cards WHERE name = '" + priceCardList.get(i).cardName + "'");
                        while (rs.next()) {
                            abilities = rs.getString("abilities");
                            if (abilities != null) {
                                abilities = abilities.replaceAll(",", "~");
                            }//end if
                            writer.append(Integer.toString(rs.getInt("id")));
                            writer.append(",");
                            writer.append("\"" + rs.getString("name").replaceAll(",", "~") + "\"");
                            writer.append(",");
                            writer.append("\"" + rs.getString("type") + "\"");
                            writer.append(",");
                            writer.append("\"" + rs.getString("subtype") + "\"");
                            writer.append(",");
                            writer.append("\"" + rs.getString("supertype") + "\"");
                            writer.append(",");
                            writer.append("\"" + rs.getString("color") + "\"");
                            writer.append(",");
                            writer.append("\"" + rs.getString("cost") + "\"");
                            writer.append(",");
                            writer.append("\"" + rs.getString("rarity") + "\"");
                            writer.append(",");
                            writer.append("\"" + abilities + "\"");
                            writer.append(",");
                            writer.append("\"" + Integer.toString(rs.getInt("power")) + "\"");
                            writer.append(",");
                            writer.append("\"" + Integer.toString(rs.getInt("toughness")) + "\"");
                            writer.append(",");
                            writer.append("\"" + Double.toString(rs.getDouble("price")) + "\"");
                            writer.append("\n");
                        }//end while
                    }//end for
                    writer.flush();
                    writer.close();
                    JOptionPane.showMessageDialog(null, "Export Complete!");
                } catch (IOException ex) {
                    showError(ex.toString(), "File Error");
                } catch (SQLException ex) {
                    showError(ex.toString(), "SQL Error");
                }//end try catch block
            }//end if
        }//end if
        else {
            showError("There is nothing to export!", "Export Error");
        }//end else
    }//GEN-LAST:event_ExportListMenuButtonActionPerformed

    private void SearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchButtonActionPerformed
        //This method searches the database. I have some things I want to change about it, but this is good for the project
        search(true);
        JOptionPane.showMessageDialog(null, "Search Done", "Search Done!", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_SearchButtonActionPerformed

    private void AddSearchedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddSearchedButtonActionPerformed
        //This method adds a searched item and it's price to the price list
        for (int i = 0; i < searchData.getSize(); i++) {
            data.addElement(searchData.get(i));
            priceCardList.add(resultCardList.get(i));
            totalPrice += resultCardList.get(i).cardPrice;
            RunningTotalLabel.setText(RunningTotalLabelString + totalPrice);
        }//end for
    }//GEN-LAST:event_AddSearchedButtonActionPerformed

    private void AddSearchSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddSearchSelectedButtonActionPerformed
        //This method adds all selected items and their prices to the price list
        searchSelected.clear();
        int[] selectedIx = this.SearchCardList.getSelectedIndices();
        for (int i = 0; i < selectedIx.length; i++) {
            data.addElement(searchData.get(selectedIx[i]));
            priceCardList.add(resultCardList.get(selectedIx[i]));
            totalPrice += resultCardList.get(selectedIx[i]).cardPrice;
            RunningTotalLabel.setText(RunningTotalLabelString + totalPrice);
        }//end for
    }//GEN-LAST:event_AddSearchSelectedButtonActionPerformed

    private void AddToTotalList(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AddToTotalList
        //This method adds all searched items and their prices to the price list
        String addToData;
        int i = SearchCardList.getSelectedIndex();
        if (evt.getClickCount() == 2) {
            addToData = SearchCardList.getSelectedValue().toString();
            data.addElement(addToData);
            priceCardList.add(resultCardList.get(i));
            totalPrice += resultCardList.get(i).cardPrice;
            RunningTotalLabel.setText(RunningTotalLabelString + totalPrice);
        }//end if
    }//GEN-LAST:event_AddToTotalList

    private void AddCardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCardButtonActionPerformed
        add();
    }//GEN-LAST:event_AddCardButtonActionPerformed

    private void SearchNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchNameTextFieldActionPerformed
        search(true);
    }//GEN-LAST:event_SearchNameTextFieldActionPerformed

    private void SearchSetComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchSetComboBoxActionPerformed
        searchBuilder();
    }//GEN-LAST:event_SearchSetComboBoxActionPerformed

    private void SearchWhiteCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchWhiteCheckBoxActionPerformed
        searchBuilder();
    }//GEN-LAST:event_SearchWhiteCheckBoxActionPerformed

    private void SearchBlueCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBlueCheckBoxActionPerformed
        searchBuilder();
    }//GEN-LAST:event_SearchBlueCheckBoxActionPerformed

    private void SearchBlackCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchBlackCheckBoxActionPerformed
        searchBuilder();
    }//GEN-LAST:event_SearchBlackCheckBoxActionPerformed

    private void SearchRedCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchRedCheckBoxActionPerformed
        searchBuilder();
    }//GEN-LAST:event_SearchRedCheckBoxActionPerformed

    private void SearchGreenCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchGreenCheckBoxActionPerformed
        searchBuilder();
    }//GEN-LAST:event_SearchGreenCheckBoxActionPerformed

    private void SearchSupertypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchSupertypeComboBoxActionPerformed
        searchBuilder();
    }//GEN-LAST:event_SearchSupertypeComboBoxActionPerformed

    private void SearchNameTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchNameTextFieldKeyReleased
        searchBuilder();
    }//GEN-LAST:event_SearchNameTextFieldKeyReleased

    private void SearchExactColorCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchExactColorCheckboxActionPerformed
        searchBuilder();
    }//GEN-LAST:event_SearchExactColorCheckboxActionPerformed

    private void SearchExcludeColorsCheckboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchExcludeColorsCheckboxActionPerformed
        searchBuilder();
    }//GEN-LAST:event_SearchExcludeColorsCheckboxActionPerformed

    private void SearchTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchTypeComboBoxActionPerformed
        searchBuilder();
    }//GEN-LAST:event_SearchTypeComboBoxActionPerformed

    private void SearchSubtypeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchSubtypeTextFieldActionPerformed
        search(true);
    }//GEN-LAST:event_SearchSubtypeTextFieldActionPerformed

    private void SearchSubtypeTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchSubtypeTextFieldKeyReleased
        searchBuilder();
    }//GEN-LAST:event_SearchSubtypeTextFieldKeyReleased

    private void SearchCostTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchCostTextFieldActionPerformed
        search(true);
    }//GEN-LAST:event_SearchCostTextFieldActionPerformed

    private void SearchCostTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchCostTextFieldKeyReleased
        searchBuilder();
    }//GEN-LAST:event_SearchCostTextFieldKeyReleased

    private void SearchRarityComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchRarityComboBoxActionPerformed
        searchBuilder();
    }//GEN-LAST:event_SearchRarityComboBoxActionPerformed

    private void SearchPowerTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchPowerTextFieldActionPerformed
        search(true);
    }//GEN-LAST:event_SearchPowerTextFieldActionPerformed

    private void SearchToughnessTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchToughnessTextFieldActionPerformed
        search(true);
    }//GEN-LAST:event_SearchToughnessTextFieldActionPerformed

    private void SearchPowerTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchPowerTextFieldKeyReleased
        searchBuilder();
    }//GEN-LAST:event_SearchPowerTextFieldKeyReleased

    private void SearchToughnessTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchToughnessTextFieldKeyReleased
        searchBuilder();
    }//GEN-LAST:event_SearchToughnessTextFieldKeyReleased

    private void SearchAbilitiesTextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchAbilitiesTextAreaKeyReleased
        searchBuilder();
    }//GEN-LAST:event_SearchAbilitiesTextAreaKeyReleased

    private void SearchPriceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchPriceTextFieldActionPerformed
        search(true);
    }//GEN-LAST:event_SearchPriceTextFieldActionPerformed

    private void SearchPriceTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SearchPriceTextFieldKeyReleased
        searchBuilder();
    }//GEN-LAST:event_SearchPriceTextFieldKeyReleased

    private void SearchPriceModifierComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SearchPriceModifierComboBoxActionPerformed
        if (SearchPriceTextField.getText().equals("")) {
            SearchPriceTextField.setText("0.0");
        }//end if
        search(true);
    }//GEN-LAST:event_SearchPriceModifierComboBoxActionPerformed

    private void AddCardNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCardNameTextFieldActionPerformed
        add();
    }//GEN-LAST:event_AddCardNameTextFieldActionPerformed

    private void AddCardNameTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddCardNameTextFieldKeyReleased
        addBuilder();
    }//GEN-LAST:event_AddCardNameTextFieldKeyReleased

    private void AddSupertypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddSupertypeComboBoxActionPerformed
        addBuilder();
    }//GEN-LAST:event_AddSupertypeComboBoxActionPerformed

    private void AddTypeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddTypeComboBoxActionPerformed
        addBuilder();
    }//GEN-LAST:event_AddTypeComboBoxActionPerformed

    private void AddWhiteCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddWhiteCheckBoxActionPerformed
        addBuilder();
    }//GEN-LAST:event_AddWhiteCheckBoxActionPerformed

    private void AddBlueCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBlueCheckBoxActionPerformed
        addBuilder();
    }//GEN-LAST:event_AddBlueCheckBoxActionPerformed

    private void AddBlackCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddBlackCheckBoxActionPerformed
        addBuilder();
    }//GEN-LAST:event_AddBlackCheckBoxActionPerformed

    private void AddRedCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddRedCheckBoxActionPerformed
        addBuilder();
    }//GEN-LAST:event_AddRedCheckBoxActionPerformed

    private void AddGreenCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddGreenCheckBoxActionPerformed
        addBuilder();
    }//GEN-LAST:event_AddGreenCheckBoxActionPerformed

    private void AddSubtypeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddSubtypeTextFieldActionPerformed
        add();
    }//GEN-LAST:event_AddSubtypeTextFieldActionPerformed

    private void AddSubtypeTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddSubtypeTextFieldKeyReleased
        addBuilder();
    }//GEN-LAST:event_AddSubtypeTextFieldKeyReleased

    private void AddCostTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddCostTextFieldActionPerformed
        add();
    }//GEN-LAST:event_AddCostTextFieldActionPerformed

    private void AddCostTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddCostTextFieldKeyReleased
        addBuilder();
    }//GEN-LAST:event_AddCostTextFieldKeyReleased

    private void AddRarityComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddRarityComboBoxActionPerformed
        addBuilder();
    }//GEN-LAST:event_AddRarityComboBoxActionPerformed

    private void AddPowerTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPowerTextFieldActionPerformed
        add();
    }//GEN-LAST:event_AddPowerTextFieldActionPerformed

    private void AddToughnessTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToughnessTextFieldActionPerformed
        add();
    }//GEN-LAST:event_AddToughnessTextFieldActionPerformed

    private void AddPowerTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddPowerTextFieldKeyReleased
        addBuilder();
    }//GEN-LAST:event_AddPowerTextFieldKeyReleased

    private void AddToughnessTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddToughnessTextFieldKeyReleased
        addBuilder();
    }//GEN-LAST:event_AddToughnessTextFieldKeyReleased

    private void AddAbilitiesTextAreaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddAbilitiesTextAreaKeyReleased
        addBuilder();
    }//GEN-LAST:event_AddAbilitiesTextAreaKeyReleased

    private void AddPriceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddPriceTextFieldActionPerformed
        add();
    }//GEN-LAST:event_AddPriceTextFieldActionPerformed

    private void AddPriceTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_AddPriceTextFieldKeyReleased
        addBuilder();
    }//GEN-LAST:event_AddPriceTextFieldKeyReleased

    private void TabbedPaneStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_TabbedPaneStateChanged
        if (TabbedPane.getSelectedIndex() == 0) {
            QueryDisplay.setText(first + searchQuery);
        }//end if
        else if (TabbedPane.getSelectedIndex() == 1) {
            QueryDisplay.setText(first + addQuery);
        }//end else if
    }//GEN-LAST:event_TabbedPaneStateChanged

    private void RefreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RefreshButtonActionPerformed
        search(false);
        JOptionPane.showMessageDialog(null, "Search list refreshed!", "Refresh", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_RefreshButtonActionPerformed

    private void BannedRestrictedComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BannedRestrictedComboBoxActionPerformed
        searchBuilder();
    }//GEN-LAST:event_BannedRestrictedComboBoxActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */

        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        /*/////////////////////////////////////
         try {
         for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
         if ("Nimbus".equals(info.getName())) {
         javax.swing.UIManager.setLookAndFeel(info.getClassName());
         break;
         }
         }
         } catch (ClassNotFoundException ex) {
         java.util.logging.Logger.getLogger(DatabaseDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (InstantiationException ex) {
         java.util.logging.Logger.getLogger(DatabaseDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (IllegalAccessException ex) {
         java.util.logging.Logger.getLogger(DatabaseDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         } catch (javax.swing.UnsupportedLookAndFeelException ex) {
         java.util.logging.Logger.getLogger(DatabaseDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
         }*//////////////////////////////////////
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new DatabaseDisplay().setVisible(true);
            }//end run
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel AddACardLabel;
    private javax.swing.JLabel AddAbilitiesLabel;
    private javax.swing.JTextArea AddAbilitiesTextArea;
    private javax.swing.JCheckBox AddBlackCheckBox;
    private javax.swing.JCheckBox AddBlueCheckBox;
    private javax.swing.JButton AddCardButton;
    private javax.swing.JLabel AddCardNameLabel;
    private javax.swing.JTextField AddCardNameTextField;
    private javax.swing.JPanel AddCardTabPanel;
    private javax.swing.JLabel AddColorLabel;
    private javax.swing.JLabel AddCostLabel;
    private javax.swing.JTextField AddCostTextField;
    private javax.swing.JCheckBox AddGreenCheckBox;
    private javax.swing.JLabel AddPowerLabel;
    private javax.swing.JTextField AddPowerTextField;
    private javax.swing.JLabel AddPriceLabel;
    private javax.swing.JTextField AddPriceTextField;
    private javax.swing.JComboBox AddRarityComboBox;
    private javax.swing.JLabel AddRarityLabel;
    private javax.swing.JCheckBox AddRedCheckBox;
    private javax.swing.JButton AddSearchSelectedButton;
    private javax.swing.JButton AddSearchedButton;
    private javax.swing.JLabel AddSubtypeLabel;
    private javax.swing.JTextField AddSubtypeTextField;
    private javax.swing.JLabel AddSuperTypeLabel;
    private javax.swing.JComboBox AddSupertypeComboBox;
    private javax.swing.JLabel AddToughnessLabel;
    private javax.swing.JTextField AddToughnessTextField;
    private javax.swing.JComboBox AddTypeComboBox;
    private javax.swing.JLabel AddTypeLabel;
    private javax.swing.JCheckBox AddWhiteCheckBox;
    private javax.swing.JLabel BannedRestricedLabel;
    private javax.swing.JComboBox BannedRestrictedComboBox;
    private javax.swing.JMenuItem ExportListMenuButton;
    private javax.swing.JMenu FileMenuButton;
    private javax.swing.JMenuItem ImportListMenuButton;
    private javax.swing.JMenuBar MenuBar;
    private javax.swing.JTextField QueryDisplay;
    private javax.swing.JButton RefreshButton;
    private javax.swing.JButton RemoveAllButton;
    private javax.swing.JButton RemoveSelectedButton;
    private javax.swing.JLabel RunningTotalLabel;
    private javax.swing.JScrollPane RunningTotalScrollPane;
    private javax.swing.JLabel SearchAbilitiesLabel;
    private javax.swing.JTextArea SearchAbilitiesTextArea;
    private javax.swing.JCheckBox SearchBlackCheckBox;
    private javax.swing.JCheckBox SearchBlueCheckBox;
    private javax.swing.JButton SearchButton;
    private javax.swing.JLabel SearchCardColorLabel;
    private javax.swing.JList SearchCardList;
    private javax.swing.JLabel SearchCostLabel;
    private javax.swing.JTextField SearchCostTextField;
    private javax.swing.JCheckBox SearchExactColorCheckbox;
    private javax.swing.JCheckBox SearchExcludeColorsCheckbox;
    private javax.swing.JCheckBox SearchGreenCheckBox;
    private javax.swing.JLabel SearchLabel;
    private javax.swing.JLabel SearchNameLabel;
    private javax.swing.JTextField SearchNameTextField;
    private javax.swing.JLabel SearchPowerLabel;
    private javax.swing.JTextField SearchPowerTextField;
    private javax.swing.JLabel SearchPriceLabel;
    private javax.swing.JComboBox SearchPriceModifierComboBox;
    private javax.swing.JTextField SearchPriceTextField;
    private javax.swing.JComboBox SearchRarityComboBox;
    private javax.swing.JLabel SearchRarityLabel;
    private javax.swing.JCheckBox SearchRedCheckBox;
    private javax.swing.JScrollPane SearchScrollPane;
    private javax.swing.JComboBox SearchSetComboBox;
    private javax.swing.JLabel SearchSetLabel;
    private javax.swing.JLabel SearchSubtypeLabel;
    private javax.swing.JTextField SearchSubtypeTextField;
    private javax.swing.JComboBox SearchSupertypeComboBox;
    private javax.swing.JLabel SearchSupertypeLabel;
    private javax.swing.JPanel SearchTabPanel;
    private javax.swing.JLabel SearchToughnessLabel;
    private javax.swing.JTextField SearchToughnessTextField;
    private javax.swing.JComboBox SearchTypeComboBox;
    private javax.swing.JLabel SearchTypeLabel;
    private javax.swing.JCheckBox SearchWhiteCheckBox;
    private javax.swing.JList SelectedCardList;
    private javax.swing.JTabbedPane TabbedPane;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables

    //This shows errors, still needs a bit more fleshing out
    private void showError(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }//end showError
    //Builds a search string

    private void searchBuilder() {
        //Flips if a search term is detected
        boolean search = false;

        //Name field, replaces all single quotes with escape charaters, to prevent injection
        String name = SearchNameTextField.getText().replaceAll("'", "''");

        //Color searching flags
        boolean white = false;
        boolean blue = false;
        boolean black = false;
        boolean red = false;

        //Color search operator
        String operator = "OR ";

        //Signals if unselected colors are excluded from search
        boolean exclude = false;

        //Tells the query builder if it needs to close a parenthesis
        boolean closeparen = false;

        //Construct the main query
        String query = "SELECT Name, Price FROM Cards WHERE ";
        String temp = "";
        String colorExcludeBuilder = "";

        //Check the name field
        if (name.length() != 0) {
            temp += "(Name LIKE '%" + name + "%') ";
            search = true;
        }//end if

        /*Check for "Match colors Exactly"*/
        if (SearchExactColorCheckbox.isSelected()) {
            operator = "AND ";
        }//end if

        /*Check for "Exclude Unselected Colors"*/
        if (SearchExcludeColorsCheckbox.isSelected()) {
            exclude = true;
        }//end if

        /*See if any/which checkboxes are checked*/
        if (SearchWhiteCheckBox.isSelected()) {
            white = true;
            if (search) { //If a name has been specified, use an AND clause
                temp += "AND (Color LIKE '%White%' ";
                closeparen = true;
            }//end if
            else {
                temp = "(Color LIKE '%White%' ";
                closeparen = true;
            }//end else
            search = true;
        }//end if
        else { //Build the color exclusion string. There's a statment like this for each color
            colorExcludeBuilder += "AND Color NOT LIKE '%White%'";
        }//end else
        if (SearchBlueCheckBox.isSelected()) {
            blue = true;
            if (white) { //If the White checkbox has been checked, append to end of the temp string
                temp += operator + " Color LIKE '%Blue%' ";
            }//end if
            else { //If not, check for name specification(same process as the White checkbox)
                if (search) {
                    temp += "AND (Color LIKE '%Blue%' ";
                    closeparen = true;
                }//end else
                else {
                    temp = "(Color LIKE '%Blue%' ";
                    closeparen = true;
                }//end else
            }//end else
            search = true;
        }//end if
        else {
            colorExcludeBuilder += "AND Color NOT LIKE '%Blue%'";
        }//end else
        if (SearchBlackCheckBox.isSelected()) {
            black = true;
            if (white || blue) {
                temp += operator + " Color LIKE '%Black%' ";
            }//end if
            else {
                if (search) {
                    temp += "AND (Color LIKE '%Black%' ";
                    closeparen = true;
                }//end else
                else {
                    temp = "Color LIKE '%Black%' ";
                }//end else
            }//end else
            search = true;
        }//end if
        else {
            colorExcludeBuilder += "AND Color NOT LIKE '%Black%'";
        }//end else
        if (SearchRedCheckBox.isSelected()) {
            red = true;
            if (white || blue || black) {
                temp += operator + " Color LIKE '%Red%' ";
            }//end if
            else {
                if (search) {
                    temp += "AND ( Color LIKE '%Red%' ";
                    closeparen = true;
                }//end else
                else {
                    temp = "Color LIKE '%Red%' ";
                }//end else
            }//end else
            search = true;
        }//end if
        else {
            colorExcludeBuilder += "AND Color NOT LIKE '%Red%'";
        }//end else
        if (SearchGreenCheckBox.isSelected()) {
            if (white || blue || black || red) {
                temp += operator + "Color LIKE '%Green%' ";
            }//end if
            else {
                if (search) {
                    temp += "AND (Color LIKE '%Green%' ";
                    closeparen = true;
                }//end else
                else {
                    temp = "Color LIKE '%Green%' ";
                }//end else
            }//end else
            search = true;
        }//end if
        else {
            colorExcludeBuilder += "AND Color NOT LIKE '%Green%'";
        }//end else

        /*Checks to see if color exclusion has been selected.
         * If yes, add the exclusion string to the query builder
         */
        if (exclude) {
            if (closeparen) {
                temp += colorExcludeBuilder + ") ";
                closeparen = false;
            }//end if
            else {
                temp += colorExcludeBuilder;
            }//end else
        }//end if
        if (closeparen) {
            temp += ") ";
        }//end if
        query += temp;

        /*Supertype searching*/
        Object checker = SearchSupertypeComboBox.getSelectedItem();
        if (!checker.equals("")) {
            if (search) {
                temp = "AND (Supertype = '" + checker + "') ";
            }//end if
            else {
                temp = "Supertype = '" + checker + "' ";
            }//end else
            query += temp;
            search = true;
        }//end if

        /*Type searching*/
        checker = SearchTypeComboBox.getSelectedItem();
        if (!checker.equals("")) {
            if (search) {
                temp = "AND (Type = '" + checker + "') ";
            }//end if
            else {
                temp = "Type = '" + checker + "' ";
            }//end else
            query += temp;
            search = true;
        }//end if

        /*Subtype searching*/
        checker = SearchSubtypeTextField.getText();
        String processor = (String) checker;
        processor = processor.replaceAll("'", "''");
        if (!checker.equals("")) {
            if (search) {
                temp = "AND (Subtype LIKE '%" + processor + "%') ";
            }//end if
            else {
                temp = "Subtype LIKE '%" + processor + "%' ";
            }//end else
            query += temp;
            search = true;
        }//end if

        /*CMC searching*/
        checker = SearchCostTextField.getText();
        processor = (String) checker;
        processor = processor.replaceAll("'", "''");
        if (!checker.equals("")) {
            if (search) {
                temp = "AND (Cost LIKE '%" + processor + "%') ";
            }//end if
            else {
                temp = "Cost LIKE '%" + processor + "%' ";
            }//end else
            query += temp;
            search = true;
        }//end if

        /*Rarity search*/
        checker = SearchRarityComboBox.getSelectedItem();
        if (!checker.equals("")) {
            if (search) {
                temp = "AND (Rarity = '" + checker + "') ";
            }//end if
            else {
                temp = "Rarity = '" + checker + "' ";
            }//end else
            query += temp;
            search = true;
        }//end if

        /*Power search*/
        checker = SearchPowerTextField.getText();
        processor = (String) checker;
        processor = processor.replaceAll("'", "''");
        if (!checker.equals("")) {
            if (search) {
                temp = "AND (Power = '" + Integer.parseInt(processor) + "') ";
            }//end if
            else {
                temp = "Power = '" + Integer.parseInt(processor) + "' ";
            }//end else
            query += temp;
            search = true;
        }//end if

        /*Toughness search*/
        checker = SearchToughnessTextField.getText();
        processor = (String) checker;
        processor = processor.replaceAll("'", "''");
        if (!checker.equals("")) {
            if (search) {
                temp = "AND (Toughness = '" + Integer.parseInt(processor) + "') ";
            }//end if
            else {
                temp = "Toughness = '" + Integer.parseInt(processor) + "' ";
            }//end else
            query += temp;
            search = true;
        }//end if

        /*Abilities search*/
        checker = SearchAbilitiesTextArea.getText();
        processor = (String) checker;
        processor = processor.replaceAll("\n", "<br/>");
        processor = processor.replaceAll("'", "''");
        if (!checker.equals("")) {
            if (search) {
                temp = "AND (Abilities LIKE '%" + processor + "%') ";
            }//end if
            else {
                temp = "Abilities LIKE '%" + processor + "%' ";
            }//end else
            query += temp;
            search = true;
        }//end if

        /*Price Search*/
        checker = SearchPriceTextField.getText();
        operator = (String) SearchPriceModifierComboBox.getSelectedItem();
        if (!checker.equals("")) {
            if (search) {
                temp = "AND (Price " + operator + " '" + Double.parseDouble((String) checker) + "') ";
            }//end if
            else {
                temp = "Price " + operator + " '" + Double.parseDouble((String) checker) + "' ";
            }//end else
            query += temp;
            search = true;
        }//end if

        //Construct the end of the query
        String end = "";
        if (!SearchSetComboBox.getSelectedItem().equals("")) {
            if (search) {
                end = " AND (Name IN (SELECT cardName FROM " + SearchSetComboBox.getSelectedItem() + "))";
            }//end if
            else {
                end = "Name IN (SELECT cardName FROM " + SearchSetComboBox.getSelectedItem() + ")";
                search = true;
            }//end else
        }//end if

        String banned = "";
        if (!BannedRestrictedComboBox.getSelectedItem().equals("")) {
            String temp2 = (String) BannedRestrictedComboBox.getSelectedItem();
            switch (temp2) {
                case "Banned as a Commander":
                    banned = "Name IN (SELECT CardName FROM CommanderBanList WHERE BanType = 'Commander')";
                    break;
                case "Banned in Commander":
                    banned = "Name IN (SELECT CardName FROM CommanderBanList WHERE BanType = 'Regular')";
                    break;
                case "Allowed as a Commander":
                    banned = "Name NOT IN (SELECT CardName FROM CommanderBanList WHERE BanType = 'Commander')";
                    break;
                case "Allowed in Commander":
                    banned = "Name NOT IN (SELECT CardName FROM CommanderBanList WHERE BanType = 'Regular')";
                    break;
                case "Banned in Vintage":
                    banned = "Name IN (SELECT CardName FROM VintageBanList WHERE BanType = 'Banned')";
                    break;
                case "Restricted in Vintage":
                    banned = "Name IN (SELECT CardName FROM VintageBanList WHERE BanType = 'Restricted')";
                    break;
                case "Allowed in Vintage":
                    banned = "Name NOT IN (SELECT CardName FROM VintageBanList)";
                    break;
                case "Banned in Legacy":
                    banned = "Name IN (SELECT CardName FROM LegacyBanList)";
                    break;
                case "Allowed in Legacy":
                    banned = "Name NOT IN (SELECT CardName FROM LegacyBanList)";
                    break;
                case "Banned in Modern":
                    banned = "Name IN (SELECT CardName FROM ModernBanList)";
                    break;
                case "Banned in Standard":
                    banned = "Name IN (SELECT CardName FROM StandardBanList)";
                    break;
            }//end switch
            if (search) {
                banned = "AND (" + banned + ")";
            }//end if
        }//end if
        searchQuery = query + end + banned + ";";
        if (searchQuery.equals("SELECT Name, Price FROM Cards WHERE ;")) {
            searchQuery = "SELECT Name, Price FROM Cards";
        }//end if
        QueryDisplay.setText(first + searchQuery);
    }//end searchBuilder

    private void search(boolean update) {
        if (update) {
            searchBuilder();
        }//end if
        else {
            searchQuery = home;
        }//end else 
        try {
            rs = stmt.executeQuery(searchQuery);
            searchData.clear();
            resultCardList.clear();
            for (int i = 0; rs.next(); i++) {
                resultCardList.add(new MTGDBResult(rs.getString("Name"), rs.getDouble("Price")));
                searchData.addElement(resultCardList.get(i).cardName);
            }//end while
        } catch (SQLException ex) {
            showError(ex.toString(), "SQL Error");
        }//end try catch block
    }//end search

    private String[] addBuilder() {
        //Start the query and error strings
        String query = "INSERT INTO Cards (id,name,type,supertype,subtype,color,cost,rarity,abilities,power,toughness,price) VALUES (0,";

        //These variables hold values to input into the database
        String name = "null";
        String type = "null";
        String supertype = "null";
        String subtype = "null";
        String colorBuilder = "null";
        String cost = "null";
        String rarity = "null";
        String abilities = "null";
        String power = "null";
        String toughness = "null";
        String price = "price";

        //Determines if the color string needs building
        boolean colorSet = false;

        if (!AddCardNameTextField.getText().equals("")) {
            name = "'" + AddCardNameTextField.getText().replaceAll("'", "''") + "'";
        }//end if
        if (!AddTypeComboBox.getSelectedItem().equals("")) {
            type = "'" + (String) AddTypeComboBox.getSelectedItem() + "'";
        }//end if
        if (!AddSupertypeComboBox.getSelectedItem().equals("")) {
            supertype = "'" + (String) AddSupertypeComboBox.getSelectedItem() + "'";
        }//end if
        if (!AddSubtypeTextField.getText().equals("")) {
            subtype = "'" + AddSubtypeTextField.getText().replaceAll("'", "''") + "'";
        }//end if

        /*This section builds the color portion of the insert statement
         * If any color is detected, flips colorSet to true
         */

        if (AddWhiteCheckBox.isSelected()) {
            colorBuilder = "'White ";
            colorSet = true;
        }//end if
        if (AddBlueCheckBox.isSelected()) { //For all colors past White, if a color was detected, append the next color
            if (colorSet) {                 //If one hasn't, simply assign colorBuilder the appropriate value
                colorBuilder += "Blue ";
            }//end if
            else {
                colorBuilder = "'Blue ";
                colorSet = true;
            }//end else
        }//end if
        if (AddBlackCheckBox.isSelected()) {
            if (colorSet) {
                colorBuilder += "Black ";
            }//end if
            else {
                colorBuilder = "'Black ";
                colorSet = true;
            }//end else
        }//end if
        if (AddRedCheckBox.isSelected()) {
            if (colorSet) {
                colorBuilder += "Red ";
            }//end if
            else {
                colorBuilder = "'Red ";
                colorSet = true;
            }//end else
        }//end if
        if (AddGreenCheckBox.isSelected()) {
            if (colorSet) {
                colorBuilder += "Green";
            }//end if
            else {
                colorBuilder = "'Green";
                colorSet = true;
            }//end else
        }//end if
        if (colorSet) {
            colorBuilder += "'";
        }//end if

        //Back to reqular variables

        if (!AddCostTextField.getText().equals("")) {
            cost = "'" + AddCostTextField.getText().replaceAll("'", "''") + "'";
        }//end if
        if (!AddRarityComboBox.getSelectedItem().equals("")) {
            rarity = "'" + (String) AddRarityComboBox.getSelectedItem() + "'";
        }//end if
        if (!AddPowerTextField.getText().equals("")) {
            power = AddPowerTextField.getText().replaceAll("'", "''");
        }//end if
        if (!AddToughnessTextField.getText().equals("")) {
            toughness = AddToughnessTextField.getText().replaceAll("'", "''");
        }//end if
        if (!AddPriceTextField.getText().equals("")) {
            price = AddPriceTextField.getText().replaceAll("'", "''");
        }//end if
        if (!AddAbilitiesTextArea.getText().equals("")) {
            abilities = "'" + AddAbilitiesTextArea.getText().replaceAll("'", "''") + "'";
        }//end if

        //Combine all variables to get the query string
        query += name + ","
                + type + ","
                + supertype + ","
                + subtype + ","
                + colorBuilder + ","
                + cost + ","
                + rarity + ","
                + abilities.replaceAll("\n", "<br/>") + ","
                + power + ","
                + toughness + ","
                + price + ")";
        addQuery = query;
        QueryDisplay.setText(first + addQuery);
        String[] out = {"0", name, type, supertype, colorBuilder, cost, rarity, abilities.replaceAll("\n", "<br/>"), power, toughness, price};
        return out;
    }//end addBuilder

    private void add() {
        String error = "The following  required fields are blank: ";

        //Flips if an input error is detected
        boolean errorDisplay = false;
        /*This section looks for missing required information(name, type, cost)
         * Flips errorDisplay is missing information is detected
         */

        if (AddCardNameTextField.getText().equals("")) {
            error += "Name ";
            errorDisplay = true;
        }//end if
        if (AddTypeComboBox.getSelectedItem().equals("")) {
            error += "Type ";
            errorDisplay = true;
        }//end if
        if (AddCostTextField.getText().equals("")) {
            error += "Cost";
            errorDisplay = true;
        }//end if 

        //If no errors, run the insert statement

        if (!errorDisplay) {
            //Double check to make sure the user wants to enter something
            String[] messageValues = addBuilder();
            String values = addQuery.substring(19, 99);
            String[] fields = values.split(",");
            String message = "Values to be added:\n";
            for (int i = 0; i < fields.length - 1; i++) {
                message += fields[i] + ": " + messageValues[i] + "\n";
            }//end for
            int eval = JOptionPane.showConfirmDialog(null, message, "Input Confirmation", JOptionPane.YES_NO_OPTION);
            try {
                rs = stmt.executeQuery("SELECT Name from Cards where name = " + messageValues[1] + "");
                if (rs.next()) {
                    eval = 1;
                    showError("Card already in database!", "Add error");
                }//end if
                //Actually insert!
                if (eval == 0) {
                    stmt.executeUpdate(addQuery);
                    JOptionPane.showMessageDialog(null, "Card Added!");
                    addQuery = "INSERT INTO Cards (id,name,type,supertype,subtype,color,cost,rarity,abilities,power,toughness,price) VALUES (0,";
                    QueryDisplay.setText(first + addQuery);
                    AddCardNameTextField.setText("");
                    AddSupertypeComboBox.setSelectedIndex(0);
                    AddTypeComboBox.setSelectedIndex(0);
                    AddSubtypeTextField.setText("");
                    AddWhiteCheckBox.setSelected(false);
                    AddBlueCheckBox.setSelected(false);
                    AddBlackCheckBox.setSelected(false);
                    AddRedCheckBox.setSelected(false);
                    AddGreenCheckBox.setSelected(false);
                    AddCostTextField.setText("");
                    AddRarityComboBox.setSelectedIndex(0);
                    AddPowerTextField.setText("");
                    AddToughnessTextField.setText("");
                    AddAbilitiesTextArea.setText("");
                    AddPriceTextField.setText("");
                }//end if
                else {
                    JOptionPane.showMessageDialog(null, "Adding canceled!", "Cancel", JOptionPane.INFORMATION_MESSAGE);
                }//end else
            } catch (SQLException ex) {
                showError(ex.toString(), "SQL Error");
            }//end try catch
        }//end if
        else {
            showError(error, "Input Error");
        }//end else
    }//end add
}// End DatabaseDisplay