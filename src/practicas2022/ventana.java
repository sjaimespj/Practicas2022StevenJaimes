package practicas2022;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class ventana extends JFrame {

    usuario usuSistema[] = new usuario[10];
    JPanel panelInicioSesion;
    JPanel panelControl;
    JPanel panelCrearUsuario;
    int control = 1;

    cliente clientes[] = new cliente[100];
    int controlCliente = 0;
    JPanel panelControlClientes;
    int controlClientes = 2;

    producto productos[] = new producto[100];
    int controlProducto = 0;
    JPanel panelControlProductos;
    int controlProductos = 2;

    public ventana() {
        objetos();
        crearAdmin();
        crearClientes();
        crearProductos();
    }

    public void crearAdmin() {
        usuSistema[0] = new usuario();
        usuSistema[0].nombreUsuario = "stevenjjp";
        usuSistema[0].nombre = "Steven";
        usuSistema[0].contra = "0411";
    }

    public void crearClientes() {
        clientes[0] = new cliente();
        clientes[0].nombre = "cliente 1";
        clientes[0].edad = 30;
        clientes[0].genero = 'F';
        clientes[0].nit = 300;

        clientes[1] = new cliente();
        clientes[1].nombre = "cliente 2";
        clientes[1].edad = 22;
        clientes[1].genero = 'M';
        clientes[1].nit = 150;
    }

    public void crearProductos() {
        productos[0] = new producto();
        productos[0].nombre = "producto 1";
        productos[0].precio = (float) 55.44;
        productos[0].cantidad = 4;

        productos[1] = new producto();
        productos[1].nombre = "producto 2";
        productos[1].precio = (float) 44.44;
        productos[1].cantidad = 3;
    }

    public void objetos() {
        panelInicioSesion = new JPanel();
        this.getContentPane().add(panelInicioSesion);
        panelInicioSesion.setLayout(null);

        JLabel lblLogin = new JLabel("Login");
        lblLogin.setBounds(20, 7, 100, 15);
        panelInicioSesion.add(lblLogin);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(60, 20, 100, 15);
        panelInicioSesion.add(lblUsuario);

        JLabel lblContra = new JLabel("Contraseña");
        lblContra.setBounds(60, 100, 100, 15);
        panelInicioSesion.add(lblContra);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(150, 20, 200, 25);
        panelInicioSesion.add(txtUsuario);

        JTextField txtContra = new JTextField();
        txtContra.setBounds(150, 100, 200, 25);
        panelInicioSesion.add(txtContra);

        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.setBounds(120, 145, 180, 35);
        panelInicioSesion.add(btnIngresar);
        ActionListener ingresar = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtUsuario.getText().equals("") || txtContra.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe llenar todos los campos");
                } else {
                    recorrerUsuarios(txtUsuario.getText(), txtContra.getText());
                }

            }

        };
        btnIngresar.addActionListener(ingresar);

        JButton btnCrearUsuario = new JButton("Registrarse");
        btnCrearUsuario.setBounds(120, 200, 180, 35);
        panelInicioSesion.add(btnCrearUsuario);
        ActionListener crearUsuario = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearUsuario();
                panelCrearUsuario.setVisible(true);
            }
        };
        btnCrearUsuario.addActionListener(crearUsuario);
    }

    public void recorrerUsuarios(String nombreUsuario, String contra) {
        boolean encontrado = false;
        for (int i = 0; i < 10; i++) {
            if (usuSistema[i] != null) {
                if (usuSistema[i].nombreUsuario.equals(nombreUsuario) && usuSistema[i].contra.equals(contra)) {
                    JOptionPane.showMessageDialog(null, "Bien venido al sistema usuario " + nombreUsuario);
                    panelControl();
                    encontrado = true;
                    break;
                } else {
                    encontrado = false;
                }
            }
        }
        if (encontrado == false) {
            JOptionPane.showMessageDialog(null, "Datos Datos incorrectos");
        }
    }

    public void panelControl() {
        panelControl = new JPanel();
        this.getContentPane().add(panelControl);
        panelControl.setLayout(null);
        this.setSize(600, 500);
        this.setTitle("Control Principal");
        panelInicioSesion.setVisible(false);

        JButton btnAdminClientes = new JButton("Administración de Clientes");
        btnAdminClientes.setBounds(150, 10, 250, 25);
        panelControl.add(btnAdminClientes);
        ActionListener administrarClientes = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelControlCli();
                panelControlClientes.setVisible(true);
            }
        };
        btnAdminClientes.addActionListener(administrarClientes);

        JButton btnAdminProductos = new JButton("Administración de Productos");
        btnAdminProductos.setBounds(150, 80, 250, 25);
        panelControl.add(btnAdminProductos);
        ActionListener administrarProductos = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                panelControlPro();
                panelControlProductos.setVisible(true);
            }
        };
        btnAdminProductos.addActionListener(administrarProductos);
        
    }

    public void crearUsuario() {
        panelCrearUsuario = new JPanel();
        this.getContentPane().add(panelCrearUsuario);
        panelCrearUsuario.setLayout(null);
        this.setSize(500, 450);
        this.setTitle("Registro de nuevo usuario");
        panelInicioSesion.setVisible(false);

        JLabel lblRegistro = new JLabel("Registro de usuario");
        lblRegistro.setBounds(20, 20, 150, 20);
        panelCrearUsuario.add(lblRegistro);

        JLabel lblUsuario = new JLabel("Usuario");
        lblUsuario.setBounds(50, 50, 150, 20);
        panelCrearUsuario.add(lblUsuario);

        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setBounds(50, 100, 150, 20);
        panelCrearUsuario.add(lblNombre);

        JLabel lblContra = new JLabel("Contraseña");
        lblContra.setBounds(50, 150, 150, 20);
        panelCrearUsuario.add(lblContra);

        JLabel lblConfirmar = new JLabel("Confirmar contraseña");
        lblConfirmar.setBounds(50, 200, 150, 20);
        panelCrearUsuario.add(lblConfirmar);

        JTextField txtUsuario = new JTextField();
        txtUsuario.setBounds(200, 50, 150, 20);
        panelCrearUsuario.add(txtUsuario);

        JTextField txtNombreUsuario = new JTextField();
        txtNombreUsuario.setBounds(200, 100, 150, 20);
        panelCrearUsuario.add(txtNombreUsuario);

        JTextField txtContra = new JTextField();
        txtContra.setBounds(200, 150, 150, 20);
        panelCrearUsuario.add(txtContra);

        JTextField txtConfContra = new JTextField();
        txtConfContra.setBounds(200, 200, 150, 20);
        panelCrearUsuario.add(txtConfContra);

        JButton btnRegistrar = new JButton("Registrar");
        btnRegistrar.setBounds(130, 280, 200, 35);
        panelCrearUsuario.add(btnRegistrar);
        ActionListener registro = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (txtUsuario.getText().equals("") || txtNombreUsuario.getText().equals("") || txtContra.getText().equals("") || txtConfContra.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Debe de llenar todos los campos");
                } else {
                    if (txtContra.getText().equals(txtConfContra.getText())) {
                        guardarUsuario(txtUsuario.getText(), txtNombreUsuario.getText(), txtContra.getText());
                        txtUsuario.setText("");
                        txtNombreUsuario.setText("");
                        txtNombreUsuario.setText("");
                        txtContra.setText("");
                        txtConfContra.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Las contraseñas NO coinsiden");
                    }
                }
            }
        };
        btnRegistrar.addActionListener(registro);

        JButton btnVolver = new JButton("Volver al inicio");
        btnVolver.setBounds(130, 350, 200, 35);
        panelCrearUsuario.add(btnVolver);
        ActionListener volverInicio = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                panelInicioSesion.setVisible(true);
                panelCrearUsuario.setVisible(false);
                volverInicio();
            }
        };
        btnVolver.addActionListener(volverInicio);
    }

    public void volverInicio() {
        this.setTitle("Sitema de administrativo de clientes y recursos");
        this.setSize(450, 350);
    }

    public void guardarUsuario(String nombre, String nombreUsuario, String contra) {
        int posicion = 0;
        if (control < 10) {
            for (int i = 0; i < 9; i++) {
                if (usuSistema[i] == null) {
                    posicion = i;
                    break;
                }
            }
            //System.out.println("La posicion libre es" + posicion);
            usuSistema[posicion] = new usuario();
            usuSistema[posicion].nombreUsuario = nombre;
            usuSistema[posicion].nombre = nombreUsuario;
            usuSistema[posicion].contra = contra;
            control++;
            JOptionPane.showMessageDialog(null, "Usuario registrado exitosamente, total de usuarios " + control);

        } else {
            JOptionPane.showMessageDialog(null, "no se puede registrar mas usuarios");
        }
    }

    public void panelControlCli() {
        panelControlClientes = new JPanel();
        this.getContentPane().add(panelControlClientes);
        panelControlClientes.setLayout(null);
        this.setSize(950, 500);
        this.setTitle("Administracion de Clientes");
        panelControl.setVisible(false);

        DefaultTableModel datosTabla = new DefaultTableModel();
        datosTabla.addColumn("Nombre");
        datosTabla.addColumn("Edad");
        datosTabla.addColumn("Genero");
        datosTabla.addColumn("Nit");

        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                String fila[] = {clientes[i].nombre, String.valueOf(clientes[i].edad), String.valueOf(clientes[i].genero), String.valueOf(clientes[i].nit)};
                datosTabla.addRow(fila);
            }
        }

        JTable tablaClientes = new JTable(datosTabla);
        JScrollPane barraTablaClientes = new JScrollPane(tablaClientes);
        barraTablaClientes.setBounds(10, 10, 300, 100);
        panelControlClientes.add(barraTablaClientes);

        DefaultPieDataset datos = new DefaultPieDataset();
        datos.setValue("Masculino", totalHombres());
        datos.setValue("Femenino", totalMujeres());

        JFreeChart graficoCircular = ChartFactory.createPieChart("Generos en el sistema", datos);
        ChartPanel panelCircular = new ChartPanel(graficoCircular);
        panelCircular.setBounds(10, 120, 300, 300);
        panelControlClientes.add(panelCircular);

        //System.out.println("Total de 18 a 30 " + rango18a30());
        //System.out.println("Total de 31 a 45 " + rango31a45());
        //System.out.println("Total de 45 o mas " + rango45mas());
        DefaultCategoryDataset datos2 = new DefaultCategoryDataset();
        datos2.addValue(rango18a30(), "18-30", "Edad");
        datos2.addValue(rango31a45(), "31-45", "Edad");
        datos2.addValue(rango45mas(), "Mayor a 45", "Edad");
        JFreeChart graficoColumnas = ChartFactory.createBarChart("Rango de edades", "Edad", "Escala", datos2, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panelColumnas = new ChartPanel(graficoColumnas);
        panelColumnas.setBounds(450, 120, 300, 300);
        panelControlClientes.add(panelColumnas);

        JButton btnCargarArchivo = new JButton("Buscar archivo CSV");
        btnCargarArchivo.setBounds(350, 10, 200, 25);
        panelControlClientes.add(btnCargarArchivo);
        ActionListener buscarArchivo = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File archivoSeleccionado;
                JFileChooser ventanaSeleccion = new JFileChooser();
                ventanaSeleccion.showOpenDialog(null);
                archivoSeleccionado = ventanaSeleccion.getSelectedFile();
                //System.out.println("La ubicacion del archivo es " + archivoSeleccionado.getPath());
                leerArchivoCSV(archivoSeleccionado.getPath());
                panelControlClientes.setVisible(false);
                panelControlCli();
            }
        };
        btnCargarArchivo.addActionListener(buscarArchivo);

        JButton btnReporte = new JButton("Crear reporte HTML");
        btnReporte.setBounds(650, 10, 200, 25);
        panelControlClientes.add(btnReporte);
        ActionListener crearHTML = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearReporte();
            }
        };
        btnReporte.addActionListener(crearHTML);

        JButton btnVolver = new JButton("Volver al menu");
        btnVolver.setBounds(650, 75, 200, 25);
        panelControlClientes.add(btnVolver);
        ActionListener volverInicio = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panelControl.setVisible(true);
                panelControlClientes.setVisible(false);
                volverInicio();
            }
        };
        btnVolver.addActionListener(volverInicio);

    }

    public void ordenar() {
        cliente actual;
        cliente adelante;
        cliente auxiliar;
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 99; j++) {
                if (clientes[j + 1] == null) {
                    break;
                } else {
                    if (clientes[j].edad > clientes[j + 1].edad) {
                        auxiliar = clientes[j + 1];
                        clientes[j + 1] = clientes[j];
                        clientes[j] = auxiliar;
                    }
                }
            }
        }
    }

    public void crearReporte() {
        try {
            ordenar();
            PrintWriter escribirCSS = new PrintWriter("reportes/estilo.css", "UTF-8");
            escribirCSS.print("html {   font-size: 20px; font-family: 'Open Sans', sans-serif; }");
            escribirCSS.print("h1 { font-size: 60px; text-align: center; }");
            escribirCSS.print("p, li {   font-size: 16px;   line-height: 2;   letter-spacing: 1px; }");
            escribirCSS.print("table { table-layout: fixed;   width:250px;}   td{border: 1px solid black; width: 190px;  word-wrap: break-word}");
            escribirCSS.print("html { background-color: #17202A; }");
            escribirCSS.print("body { width: 970px; margin: 0 auto; background-color: #00FFE4; padding: 0 20px 20px 20px; border: 5px solid white; }");
            escribirCSS.print("h1 { margin: 0; padding: 20px 0; color: #F70505; text-shadow: 3px 3px 1px black; }");
            escribirCSS.close();

            PrintWriter escribir = new PrintWriter("reportes/index.html", "UTF-8");
            escribir.println("<!doctype hmtl>");
            escribir.println("<html>");
            escribir.println("<head>");
            escribir.println("<title>Reporte del sistema </title>");
            escribir.println("<link rel=\"stylesheet\" href=\"estilo.css\">");
            escribir.println("</head>");
            escribir.println("<body>");
            escribir.println("<h1>Listado de clientes en el sistema</h1>");
            escribir.println("<br>");

            escribir.println("<table border = 1>");
            escribir.println("<tr>");
            escribir.println("<td>NIT</td> <td>Nombre</td> <td>Edad</td> <td>Genero</td>");
            escribir.println("</tr>");

            for (int i = 0; i < 99; i++) {
                if (clientes[i] != null) {
                    escribir.println("<tr>");
                    escribir.println("<td>" + clientes[i].nit + "</td><td>" + clientes[i].nombre + "</td><td>" + clientes[i].edad + "</td><td>" + clientes[i].genero + "</td>");
                    escribir.println("</tr>");
                }
            }

            escribir.println("</table>");

            escribir.println("</body>");
            escribir.println("</html>");

            escribir.close();
            JOptionPane.showMessageDialog(null, "Reporte creado con exito, esta se encuentra en la carpeta REPORTES");
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "No se pudo crear el reporte");
        }
    }

    public int totalHombres() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].genero == 'M') {
                    total++;
                }
            }

        }
        return total;
    }

    public int totalMujeres() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].genero == 'F') {
                    total++;
                }
            }

        }
        return total;
    }

    public int rango18a30() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].edad >= 18 && clientes[i].edad <= 30) {
                    total++;
                }
            }

        }
        return total;
    }

    public int rango31a45() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].edad >= 31 && clientes[i].edad <= 45) {
                    total++;
                }
            }

        }
        return total;
    }

    public int rango45mas() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
                if (clientes[i].edad > 45) {
                    total++;
                }
            }

        }
        return total;
    }

    public void leerArchivoCSV(String ruta) {
        try {
            BufferedReader archivoTemporal = new BufferedReader(new FileReader(ruta));
            String lineaLeida = "";
            while (lineaLeida != null) {
                lineaLeida = archivoTemporal.readLine();
                if (lineaLeida != null) {
                    String datosSeparados[] = lineaLeida.split(",");

                    int posicion = 0;
                    if (control < 100) {
                        for (int i = 0; i < 99; i++) {
                            if (clientes[i] == null) {
                                posicion = i;
                                break;
                            }
                        }
                        clientes[posicion] = new cliente();
                        clientes[posicion].nombre = datosSeparados[0];
                        clientes[posicion].edad = Integer.parseInt(datosSeparados[1]);
                        clientes[posicion].genero = datosSeparados[2].charAt(0);
                        clientes[posicion].nit = Integer.parseInt(datosSeparados[3]);
                        controlClientes++;
                    } else {
                        JOptionPane.showMessageDialog(null, "no se puede registrar mas clientes");
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Clientes registrados exitosamente, total de clientes " + controlClientes);
            archivoTemporal.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "No pudo abrir el archivo CSV");
        }
    }

    public void panelControlPro() {
        panelControlProductos = new JPanel();
        this.getContentPane().add(panelControlProductos);
        panelControlProductos.setLayout(null);
        this.setSize(750, 500);
        this.setTitle("Administracion de Productos");
        panelControl.setVisible(false);

        DefaultTableModel datosTabla = new DefaultTableModel();
        datosTabla.addColumn("Nombre");
        datosTabla.addColumn("Precio");
        datosTabla.addColumn("Cantidad");

        for (int i = 0; i < 100; i++) {
            if (productos[i] != null) {
                String fila[] = {productos[i].nombre, String.valueOf(productos[i].precio), String.valueOf(productos[i].cantidad)};
                datosTabla.addRow(fila);
            }
        }
        JTable tablaProductos = new JTable(datosTabla);
        JScrollPane barraTablaProductos = new JScrollPane(tablaProductos);
        barraTablaProductos.setBounds(10, 10, 300, 100);
        panelControlProductos.add(barraTablaProductos);
        
        //System.out.println("Total de 20 a 60 " + precio20a60());
        //System.out.println("Total de 61 a 150 " + precio61a150());
        //System.out.println("Total de 160 o mas " + precio160mas());
        DefaultCategoryDataset datos2 = new DefaultCategoryDataset();
        datos2.addValue(precio20a60(), "18-30", "Edad");
        datos2.addValue(precio160mas(), "31-45", "Edad");
        datos2.addValue(rango45mas(), "Mayor a 45", "Edad");
        JFreeChart graficoColumnas = ChartFactory.createBarChart("Cantidad de Productos", "Precio", "Escala", datos2, PlotOrientation.VERTICAL, true, true, false);
        ChartPanel panelColumnas = new ChartPanel(graficoColumnas);
        panelColumnas.setBounds(10, 120, 300, 300);
        panelControlProductos.add(panelColumnas);
        
        JButton btnCargarArchivo2 = new JButton("Buscar archivoCSV");
        btnCargarArchivo2.setBounds(350, 10, 200, 25);
        panelControlProductos.add(btnCargarArchivo2);
        ActionListener buscarArchivo2 = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                File archivoSeleccionado;
                JFileChooser ventanaSeleccion = new JFileChooser();
                ventanaSeleccion.showOpenDialog(null);
                archivoSeleccionado = ventanaSeleccion.getSelectedFile();
                if (archivoSeleccionado == null){
                    JOptionPane.showMessageDialog(null, "Cancelado");
                }else{
                    leerArchivoCSV2(archivoSeleccionado.getPath());
                    panelControlProductos.setVisible(false);
                    panelControlPro();
                }
            }
        };
        btnCargarArchivo2.addActionListener(buscarArchivo2);
        
        JButton btnReporte2 = new JButton("Crear reporte HTML");
        btnReporte2.setBounds(450, 50, 200, 25);
        panelControlProductos.add(btnReporte2);
        ActionListener crearHTML = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearReporte2();
            }
        };
        btnReporte2.addActionListener(crearHTML);
        
        JButton btnVolver = new JButton("Volver al menu");
        btnVolver.setBounds(350, 85, 200, 25);
        panelControlProductos.add(btnVolver);
        ActionListener volverInicio = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                panelControl.setVisible(true);
                panelControlProductos.setVisible(false);
                volverInicio();
            }
        };
        btnVolver.addActionListener(volverInicio);
    }
    public void ordenar2() {
        producto actual;
        producto adelante;
        producto auxiliar;
        for (int i = 0; i < 99; i++) {
            for (int j = 0; j < 99; j++) {
                if (productos[j + 1] == null) {
                    break;
                } else {
                    if (productos[j].precio > productos[j + 1].precio) {
                        auxiliar = productos[j + 1];
                        productos[j + 1] = productos[j];
                        productos[j] = auxiliar;
                    }
                }
            }
        }
    }
    
    public void crearReporte2(){
        try{
            ordenar2();
            PrintWriter escribirCSS = new PrintWriter("reportesProductos/estilo.css", "UTF-8");
            escribirCSS.print("html {   font-size: 20px; font-family: 'Open Sans', sans-serif; }");
            escribirCSS.print("h1 { font-size: 60px; text-align: center; }");
            escribirCSS.print("p, li {   font-size: 16px;   line-height: 2;   letter-spacing: 1px; }");
            escribirCSS.print("table { table-layout: fixed;   width:250px;}   td{border: 1px solid black; width: 190px;  word-wrap: break-word}");
            escribirCSS.print("html { background-color: #17202A; }");
            escribirCSS.print("body { width: 970px; margin: 0 auto; background-color: #00FFE4; padding: 0 20px 20px 20px; border: 5px solid white; }");
            escribirCSS.print("h1 { margin: 0; padding: 20px 0; color: #F70505; text-shadow: 3px 3px 1px black; }");
            escribirCSS.close();
            
            PrintWriter escribir = new PrintWriter("reportesProductos/index.html","UTF-8");
            escribir.println("<!doctype hmtl>");
            escribir.println("<html>");
            escribir.println("<head>");
            escribir.println("<title>Reporte del sistema </title>");
            escribir.println("<link rel=\"stylesheet\" href=\"estilo.css\">");
            escribir.println("</head>");
            escribir.println("<body>");
            escribir.println("<h1>Listado de productos en el sistema</h1>");
            escribir.println("<br>");
            
            escribir.println("<table border = 1>");
            escribir.println("<tr>");
            escribir.println("<td>Nombre</td> <td>Precio</td> <td>Cantidad</td>");
            escribir.println("</tr>");
            
            for (int i = 0; i < 99; i++) {
                if (productos[i] != null) {
                    escribir.println("<tr>");
                    escribir.println("<td>" + productos[i].nombre + "</td><td>" + productos[i].precio + "</td><td>" + productos[i].cantidad + "</td>");
                    escribir.println("</tr>");
                }
            }
            
            escribir.println("</table>");

            escribir.println("</body>");
            escribir.println("</html>");
            
            escribir.close();
            JOptionPane.showMessageDialog(null, "Reporte creado con exito, esta se encuentra en la carpeta REPORTESPRODUCTOS");
        }catch(IOException error){
            JOptionPane.showMessageDialog(null, "No se pudo realizar el reporte");
        }
    }
    
    public int precio20a60() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (productos[i] != null) {
                if (productos[i].precio >=20 && productos[i].precio <=60 ){
                    total++;
                }
            }

        }
        return total;
    }
    
    public int precio61a150() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (productos[i] != null) {
                if (productos[i].precio >=61 && productos[i].precio <=150 ){
                    total++;
                }
            }

        }
        return total;
    }
    
    public int precio160mas() {
        int total = 0;
        for (int i = 0; i < 100; i++) {
            if (productos[i] != null) {
                if (productos[i].precio >=160){
                    total++;
                }
            }

        }
        return total;
    }

    public void leerArchivoCSV2(String ruta2) {
        try {
            BufferedReader archivoTemporal2 = new BufferedReader(new FileReader(ruta2));
            String lineaLeida = "";
            while (lineaLeida != null) {
                lineaLeida = archivoTemporal2.readLine();
                if (lineaLeida != null) {
                    String datosSeparados[] = lineaLeida.split(",");

                    int posicion = 0;
                    if (controlProducto < 100) {
                        for (int i = 0; i < 99; i++) {
                            if (productos[i] == null) {
                                posicion = i;
                                break;
                            }
                        }
                        productos[posicion] = new producto();
                        productos[posicion].nombre = datosSeparados[0];
                        productos[posicion].precio = Float.parseFloat(datosSeparados[1]);
                        productos[posicion].cantidad = Integer.parseInt(datosSeparados[2]);
                        controlProductos++;
                    } else {
                        JOptionPane.showMessageDialog(null, "no se puede registrar mas productos");
                    }
                }
            }
            JOptionPane.showMessageDialog(null, "Productos registrados exitosamente, total de producots " + controlProductos);
            archivoTemporal2.close();
        } catch (IOException error) {
            JOptionPane.showMessageDialog(null, "No pudo abrir el archivo CSV");
        }
    }
}
