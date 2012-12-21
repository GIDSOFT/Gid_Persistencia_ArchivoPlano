/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package packato;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Date;
import java.io.*;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRCsvDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.log4j.BasicConfigurator;
import com.nilo.plaf.nimrod.NimRODLookAndFeel;
import com.nilo.plaf.nimrod.NimRODTheme;
import java.net.MalformedURLException;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import com.sun.jndi.toolkit.ctx.Continuation;
import sun.util.locale.StringTokenIterator;

/**
 *
 * @author Administrador
 */
public class Usuarios extends javax.swing.JFrame {

    File fileLf;
    ArrayList idcbo;
    ArrayList cities;
    ArrayList eps;
    ArrayList user;
    Date date;
    String sex;
    String id;
    String name;
    String lastName;
    String idnumber;
    String dateBorn;
    int age;
    boolean foundedOrNot;
    boolean validation;
    boolean error;
    String city;
    String epschoosed;
    String nameResult;
    String lastNameResult;
    File archivo;
    FileReader fr;
    BufferedReader br;
    String linea;
    String lineaValidator;
    String idValidator;
    String[] info;
    String[] infoValidator;
    String[] lineFound;

    /**
     * Creates new form Usuarios
     */
    public Usuarios() {
        super("Registro de Usuarios");
        this.setIconImage(Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("resources/icono.png")));
        initComponents();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        idcbo = new ArrayList();
        cities = new ArrayList();
        eps = new ArrayList();
        user = new ArrayList();
        foundedOrNot = false;
        validation = true; //No pasa nada, NO existe una id igual.
        sex = "";
        Idcbo();
        cities();
        eps();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        date = new Date();
        DatePicker.setDate(date);
        DatePicker.setFormats("dd-MM-yyyy");
        archivo = null;
        fr = null;
        br = null;
        linea = null;
        lblAviso.setText("");
        info = null;
        lineFound = null;
        info = null;
        fileLf = null;
        fillTable();
        error=false;

    }

    /**
     * Este método llena el compo de tipo de identificación
     */
    private void Idcbo() {
        idcbo.add("--Seleccione--");
        idcbo.add("Cédula de ciudadania");
        idcbo.add("Tarjeta de identidad");

        for (int i = 0; i < idcbo.size(); i++) {
            cboTypeId.addItem(idcbo.get(i));
        }
        cboTypeId.setSelectedItem("--Seleccione--");

    }

    private void cities() {
        cities.add("--Seleccione un municipio--");
        cities.add("Alcalá");
        cities.add("Ansermanuevo");
        cities.add("Argelia");
        cities.add("Bolívar");
        cities.add("Cartago");
        cities.add("El Aguila");
        cities.add("El Cairo");
        cities.add("El Dovio");
        cities.add("La Unión");
        cities.add("La Victoria");
        cities.add("Obando");
        cities.add("Roldanillo");
        cities.add("Toro");
        cities.add("Ulloa");
        cities.add("Versalles");
        cities.add("Zarzal");
        cities.add("Andalucía");
        cities.add("Buga ");
        cities.add("Bugalagrande");
        cities.add("Calima Darién");
        cities.add("El Cerrito");
        cities.add("Ginebra");
        cities.add("Guacarí");
        cities.add("Restrepo");
        cities.add("Riofrío");
        cities.add("San Pedro");
        cities.add("Trujillo");
        cities.add("Tuluá");
        cities.add("Yotoco");
        cities.add("Buenaventura");
        cities.add("Caicedonia");
        cities.add("Sevilla");
        cities.add("Cali");
        cities.add("Candelaria");
        cities.add("Dagua");
        cities.add("Florida");
        cities.add("Jamundí");
        cities.add("La Cumbre");
        cities.add("Palmira");
        cities.add("Pradera");
        cities.add("Vijes");
        cities.add("Yumbo");

        for (int i = 0; i < cities.size(); i++) {
            cboCity.addItem(cities.get(i));
        }
        cboCity.setSelectedItem("--Seleccione un municipio--");
    }

    /**
     * Este metodo llena el compo de Eps
     */
    private void eps() {
        eps.add("--Seleccione--");
        eps.add("SaludCoop");
        eps.add("Compensar");
        eps.add("Comfenalco");
        eps.add("Coomeva");
        for (int i = 0; i < eps.size(); i++) {
            cboEps.addItem(eps.get(i));
        }
        cboEps.setSelectedItem("--Seleccione--");
    }

    /**
     * Este método llena la tabla
     */
    private void fillTable() {
        FileInputStream fi = null;
        Reader r = null;
        String line = null;
        File f = null;

        try {
            String separator = System.getProperty("file.separator");
            f = new File(System.getProperty("user.dir").concat(separator).concat("file.csv"));
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            String[] info = null;
            fi = new FileInputStream(f);
            r = new InputStreamReader(fi);
            int counterLines = 0;
            while ((line = br.readLine()) != null) {

                info = line.split(";");


                int numLin;
                table.setValueAt(info[0], counterLines, 1);
                table.setValueAt(info[1], counterLines, 2);
                table.setValueAt(info[2], counterLines, 3);
                table.setValueAt(info[3], counterLines, 4);
                table.setValueAt(info[4], counterLines, 5);
                table.setValueAt(info[5], counterLines, 6);
                table.setValueAt(info[6], counterLines, 7);
                table.setValueAt(info[7], counterLines, 8);
                table.setValueAt(info[8], counterLines, 9);
                counterLines++;



            }



        } catch (Exception e) {
//           JOptionPane.showConfirmDialog(null,  "Error en el método FillTable tipo ::"+"\n"+e+"\n"+e.getStackTrace());
        }
    }

    private void indentificationValidator() {
        FileInputStream fi = null;
        Reader r = null;
        String idCoincidence = txtId.getText();
        File f = null;
        try {
            String separator = System.getProperty("file.separator");
            f = new File(System.getProperty("user.dir").concat(separator).concat("file.csv"));
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            fi = new FileInputStream(f);
            r = new InputStreamReader(fi);
            while ((lineaValidator = br.readLine()) != null) {
                infoValidator = lineaValidator.split(";");
                idValidator = infoValidator[1];
                if (idCoincidence.equals(idValidator)) {
                    validation = true;
                    break;

                } else {
                    validation = false;
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en el método IdentificationValidator tipo ::" + "\n" + e + "\n" + e.getStackTrace());
        }

    }

    private void queryDoc() {
        archivo = null;
        fr = null;
        br = null;
        FileInputStream fi = null;
        Reader r = null;
        linea = null;

        File f = null;
        try {
            String separator = System.getProperty("file.separator");
            f = new File(System.getProperty("user.dir").concat(separator).concat("file.csv"));
            fr = new FileReader(f);
            br = new BufferedReader(fr);
            fi = new FileInputStream(f);
            r = new InputStreamReader(fi);
            String userSearch = txtSearchUser.getText();
            String lineFoundString;


            java.awt.event.KeyEvent evt = null;
            while ((linea = br.readLine()) != null) {
                info = linea.split(";");
                String encontrado = info[1];
                nameResult = info[2];
                lastNameResult = info[3];
                if (userSearch.equals(encontrado)) {
                    foundedOrNot = true;
                    Toolkit.getDefaultToolkit().beep();
                    System.out.println("Encontrado >>>>" + encontrado);
                    lineFoundString = linea;
                    lineFound = linea.split(";");
                    break;

                }
            }
        } catch (Exception ex) {
            System.out.println("Error en el método QueryDoc ::" + "\n" + ex);
        }

        if (foundedOrNot == true) {
            lblAviso.setForeground(Color.green.darker());
            lblAviso.setText("Encontrado como::".concat(nameResult).concat(" ").concat(lastNameResult));
            foundedOrNot = false;


        } else if (foundedOrNot == false) {
            lblAviso.setForeground(Color.red.darker());
            lblAviso.setText("Numero de identificación no encontrado.");
        }

    }

    /**
     * Este metodo escribe el contenido del archivo.txt =)
     *
     * @throws URISyntaxException
     */
    public void writingFile() throws URISyntaxException {
        if (rMale.isSelected() == true) {
            sex = "Masculino";
        } else {
            sex = "Femenino";
        }

        Desktop desk = Desktop.getDesktop();
        String separator = ";";
        id = String.valueOf(cboTypeId.getSelectedItem());
        name = txtName.getText();
        lastName = txtLastName.getText();
        idnumber = txtId.getText();
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        dateBorn = String.valueOf(format.format(DatePicker.getDate()));
        age = (this.date.getYear()) - (DatePicker.getDate().getYear());
        city = String.valueOf(cboCity.getSelectedItem());
        epschoosed = String.valueOf(cboEps.getSelectedItem());

        File file = null;
        try {
            String separatorFile = System.getProperty("file.separator");
            file = new File(System.getProperty("user.dir").concat(separatorFile).concat("file.csv"));
            FileWriter writeFile = new FileWriter(file, true);
            String ln = System.getProperty("line.separator");
            writeFile.write(id.concat(separator).concat(idnumber).concat(separator).concat(name).concat(separator).concat(lastName).concat(separator).concat(dateBorn).concat(separator).concat(String.valueOf(age)).concat(separator).concat(sex).concat(separator).concat(city).concat(separator).concat(epschoosed).concat(ln));
            writeFile.close();
//            desk.open(file);

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error en el método WritingFile tipo ::" + "\n" + ex);
        }

    }

    private JRCsvDataSource getDataSource() throws JRException, URISyntaxException {
        String[] columnNames = new String[]{"Tipo de identificación", "Identificación", "Nombre", "Apellidos", "Fecha de nacimiento", "Edad", "Sexo", "Ciudad", "Eps"};
        File fi = null;
        String separatorFile = System.getProperty("file.separator");
        fi = new File(System.getProperty("user.dir").concat(separatorFile).concat("file.csv"));
        String filePath = fi.getAbsolutePath().toString();
        JRCsvDataSource ds = new JRCsvDataSource(filePath);
        ds.setRecordDelimiter("\r\n");
        ds.setFieldDelimiter(';');
        ds.setColumnNames(columnNames);
        return ds;
    }

    private void report() throws JRException {
        File f = null;
        try {
            String separator = System.getProperty("file.separator");
            f = new File(System.getProperty("user.dir").concat(separator).concat("CsvReport.jasper"));
            System.out.println("ruta del archivo CSV>>>>"+f);
            String filePath = f.getAbsolutePath().toString();
            System.out.println("" + filePath);
            JasperReport reporte = (JasperReport) JRLoader.loadObjectFromFile(filePath);
            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, null, getDataSource());
            JasperViewer view = new JasperViewer(jasperPrint);
            view.setTitle("Reporte Informativo");
            view.setVisible(true);
        } catch (Exception exec) {
            System.out.println("" + exec);
        }
    }
    
    public boolean ValidateEmptyFields(){
        String  id= txtId.getText(); 
        String  name= txtName.getText();
        String  lastName= txtLastName.getText();
        String nameTrim=lastName.trim();
        String lastNameTrim=name.trim();
        String idTrim=id.trim();
        String typeIdValidation=cboTypeId.getSelectedItem().toString();
        String cityValidation= cboCity.getSelectedItem().toString();
        String epsValidation= cboEps.getSelectedItem().toString();
        
        
        if ((nameTrim.isEmpty()==true)||(lastNameTrim.isEmpty()==true)||(idTrim.isEmpty()==true) || (typeIdValidation.equals("--Seleccione--") ||(cityValidation.equals("--Seleccione un municipio--") || (epsValidation.equals("--Seleccione--"))))) {
       error=true;     
        }else{error=false;}
        return error;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BotonGroup = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        rMale = new javax.swing.JRadioButton();
        rFemale = new javax.swing.JRadioButton();
        jLabel9 = new javax.swing.JLabel();
        cboCity = new javax.swing.JComboBox();
        txtName = new javax.swing.JTextField();
        cboTypeId = new javax.swing.JComboBox();
        cboEps = new javax.swing.JComboBox();
        jSeparator1 = new javax.swing.JSeparator();
        btnSaveUser = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtSearchUser = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        DatePicker = new org.jdesktop.swingx.JXDatePicker();
        txtId = new javax.swing.JTextField();
        txtLastName = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        lblAviso = new org.jdesktop.swingx.JXLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel1.setText("INGRESAR USUARIO");

        jLabel2.setText("Tipo de identificación");

        jLabel3.setText("Nombres");

        jLabel4.setText("Fecha de nacimiento");

        jLabel5.setText("Ciudad de residencia");

        jLabel6.setText("Identificación");

        jLabel7.setText("Apellidos");

        jLabel8.setText("Sexo");

        BotonGroup.add(rMale);
        rMale.setText("Masculino");
        rMale.setToolTipText("Seleccione uno");

        BotonGroup.add(rFemale);
        rFemale.setText("Femenino");

        jLabel9.setText("EPS");

        cboCity.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        cboTypeId.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboTypeId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTypeIdActionPerformed(evt);
            }
        });

        cboEps.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboEps.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEpsActionPerformed(evt);
            }
        });

        btnSaveUser.setIcon(new javax.swing.ImageIcon("C:\\Documents and Settings\\Administrador\\Escritorio\\iconos\\Onebit\\01\\PNG\\onebit_11-20px.png")); // NOI18N
        btnSaveUser.setText("Guardar usuario");
        btnSaveUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveUserActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Serif", 1, 14)); // NOI18N
        jLabel10.setText("LISTADO DE USUARIOS");

        jLabel11.setText("Buscar usuario por documento de identidad");

        txtSearchUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSearchUserActionPerformed(evt);
            }
        });
        txtSearchUser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtSearchUserKeyTyped(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"1", null, null, null, null, null, null, null, null, null},
                {"2", null, null, null, null, null, null, null, null, null},
                {"3", null, null, null, null, null, null, null, null, null},
                {"4", null, null, null, null, null, null, null, null, null},
                {"5", null, null, null, null, null, null, null, null, null},
                {"6", null, null, null, null, null, null, null, null, null},
                {"7", null, null, null, null, null, null, null, null, null},
                {"8", null, null, null, null, null, null, null, null, null},
                {"9", null, null, null, null, null, null, null, null, null},
                {"10", null, null, null, null, null, null, null, null, null},
                {"11", null, null, null, null, null, null, null, null, null},
                {"12", null, null, null, null, null, null, null, null, null},
                {"13", null, null, null, null, null, null, null, null, null},
                {"14", null, null, null, null, null, null, null, null, null},
                {"15", null, null, null, null, null, null, null, null, null},
                {"16", null, null, null, null, null, null, null, null, null},
                {"17", null, null, null, null, null, null, null, null, null},
                {"18", null, null, null, null, null, null, null, null, null},
                {"19", null, null, null, null, null, null, null, null, null},
                {"20", null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "#", "Tipo de identificación", "Identificación", "Nombres", "Apellidos", "Fecha de nacimiento", "Edad", "Sexo", "Ciudad de residencia", "Eps"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        DatePicker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DatePickerActionPerformed(evt);
            }
        });

        txtId.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtIdKeyTyped(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon("C:\\Documents and Settings\\Administrador\\Escritorio\\iconos\\Onebit\\01\\PNG\\onebit_39-20px.png")); // NOI18N
        jButton1.setText("Reporte de usuarios");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon("C:\\Documents and Settings\\Administrador\\Escritorio\\iconos\\Onebit\\01\\PNG\\onebit_02-20px.png")); // NOI18N
        jButton2.setText("buscar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        lblAviso.setForeground(new java.awt.Color(204, 204, 204));
        lblAviso.setText("Found/notFound");
        lblAviso.setFont(new java.awt.Font("Verdana", 1, 12)); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 857, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel5))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtName)
                                    .addComponent(cboTypeId, 0, 118, Short.MAX_VALUE)
                                    .addComponent(cboCity, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(DatePicker, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel6)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(4, 4, 4))
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(22, 22, 22)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel8)
                                                .addComponent(jLabel7))
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(txtLastName)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(46, 46, 46)
                                        .addComponent(jLabel9)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(rMale)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(rFemale))
                                            .addComponent(cboEps, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jSeparator1))
                        .addGap(70, 70, 70))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton2))
                                    .addComponent(lblAviso, javax.swing.GroupLayout.PREFERRED_SIZE, 603, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel10)
                            .addComponent(btnSaveUser))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(cboTypeId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(txtLastName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(DatePicker, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(rMale)
                            .addComponent(rFemale))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cboCity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9)
                            .addComponent(cboEps, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21)
                .addComponent(btnSaveUser)
                .addGap(18, 18, 18)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtSearchUser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblAviso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboEpsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEpsActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboEpsActionPerformed

    private void btnSaveUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaveUserActionPerformed
   ValidateEmptyFields(); // valida que el formulario este completamente diligenciado
        
   if(error==false){     
        
        indentificationValidator();

        if (validation == false) { // o sea, Falso!! no hay un numero de identificacion igual en el archivo, entonces se procede.

            try {
                writingFile();
                fillTable();
                user.add(name);
                validation = true;
            } catch (Exception em) {
                JOptionPane.showMessageDialog(null, "Error en el boton que guarda el usuario ::" + "\n" + em);
            }
        } else {
            JOptionPane.showMessageDialog(null, "El numero de identificación ya existe.");
            validation = true;
        }
        cboTypeId.setSelectedItem("--Seleccione--");
        cboCity.setSelectedItem("--Seleccione un municipio--");
        cboEps.setSelectedItem("--Seleccione--");
        DatePicker.setDate(new Date());
        BotonGroup.clearSelection();
        txtId.setText("");
        txtName.setText("");
        txtLastName.setText("");
        txtSearchUser.setText("");
   }else{Toolkit.getDefaultToolkit().beep();JOptionPane.showMessageDialog(null, "ERROR!! \n Por favor diligencie completamente el formulario");
   }

    }//GEN-LAST:event_btnSaveUserActionPerformed

    private void cboTypeIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTypeIdActionPerformed
    }//GEN-LAST:event_cboTypeIdActionPerformed

    private void DatePickerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DatePickerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DatePickerActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            report();
        } catch (Exception e) {
            System.out.println("" + e);
        }
// TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        queryDoc();        // TODO add your handling code here:

    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtSearchUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSearchUserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSearchUserActionPerformed

    private void txtIdKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }

    }//GEN-LAST:event_txtIdKeyTyped

    private void txtSearchUserKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSearchUserKeyTyped
        char car = evt.getKeyChar();
        if ((car < '0' || car > '9')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtSearchUserKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws MalformedURLException {
        BasicConfigurator.configure();

        try {
            UIManager.setLookAndFeel(new NimRODLookAndFeel());



            NimRODLookAndFeel nf = new NimRODLookAndFeel();
            NimRODTheme nt = new NimRODTheme("C:/Documents and Settings/Administrador/Mis documentos/NetBeansProjects/GID_AlmacenamientoPersistente/src/resources/OkyDarkTheme.theme");
            nf.setCurrentTheme(nt);
            UIManager.setLookAndFeel(nf);
        } catch (UnsupportedLookAndFeelException ex) {
            System.err.println("Error de " + ex);
        }

        //</editor-fold>


        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Usuarios().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup BotonGroup;
    private org.jdesktop.swingx.JXDatePicker DatePicker;
    private javax.swing.JButton btnSaveUser;
    private javax.swing.JComboBox cboCity;
    private javax.swing.JComboBox cboEps;
    private javax.swing.JComboBox cboTypeId;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private org.jdesktop.swingx.JXLabel lblAviso;
    private javax.swing.JRadioButton rFemale;
    private javax.swing.JRadioButton rMale;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtLastName;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtSearchUser;
    // End of variables declaration//GEN-END:variables
}
