package practicas2022;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class ventana extends JFrame {

    usuario usuSistema[] = new usuario[10];
    JPanel panelInicioSesion = new JPanel();
    JPanel panelControl = new JPanel();
    JPanel panelCrearUsuario = new JPanel();
    int control = 1;
    cliente clientes[] = new cliente[100];
    int controlCliente = 0;
    JPanel panelControlClientes = new JPanel(); 

    public ventana() {
        objetos();
        crearAdmin();
        crearClientes();
    }

    public void crearAdmin() {
        usuSistema[0] = new usuario();
        usuSistema[0].nombreUsuario = "admin";
        usuSistema[0].nombre = "administrador";
        usuSistema[0].contra = "123456";
    }
    public void crearClientes(){
        clientes[0] = new cliente();
        clientes[0].nombre = "cliente 2";
        clientes[0].edad = 30;
        clientes[0].genero = 'F';
        clientes[0].nit = 300;
        
        clientes[1] = new cliente();
        clientes[1].nombre = "cliente 1";
        clientes[1].edad = 22;
        clientes[1].genero = 'M';
        clientes[1].nit = 150;
    }

    public void objetos() {
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
        this.getContentPane().add(panelControl);
        panelControl.setLayout(null);
        this.setSize(600, 500);
        this.setTitle("Control Principal");
        panelInicioSesion.setVisible(false);

        JButton btnAdminClientes = new JButton("Administración de Clientes");
        btnAdminClientes.setBounds(150, 10, 250, 25);
        panelControl.add(btnAdminClientes);
        ActionListener admininistrarClientes = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                panelControlCli();
                panelControlClientes.setVisible(true);
            }
        };
        btnAdminClientes.addActionListener(admininistrarClientes);

        JButton btnAdminProductos = new JButton("Administración de Productos");
        btnAdminProductos.setBounds(150, 80, 250, 25);
        panelControl.add(btnAdminProductos);

        JButton btnReportes = new JButton("Reportes");
        btnReportes.setBounds(150, 150, 250, 25);
        panelControl.add(btnReportes);
    }

    public void crearUsuario() {
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
            
        }else{
            JOptionPane.showMessageDialog(null, "no se puede registrar mas usuarios");
        }
    }
    public void panelControlCli(){
        this.getContentPane().add(panelControlClientes);
        panelControlClientes.setLayout(null);
        this.setSize(750, 500);
        this.setTitle("Administracion de Clientes");
        panelControl.setVisible(false);
        
        DefaultTableModel datosTabla = new DefaultTableModel();
        datosTabla.addColumn("Nombre");
        datosTabla.addColumn("Edad");
        datosTabla.addColumn("Genero");
        datosTabla.addColumn("Nit");
        
        for (int i = 0; i < 100; i++) {
            if (clientes[i] != null) {
              String fila [] = {clientes[i].nombre, String.valueOf(clientes[i].edad), String.valueOf(clientes[i].genero), String.valueOf(clientes[i].nit)}; 
              datosTabla.addRow(fila);
            }
        }
        
        
        
        
        
        JTable tablaClientes = new JTable(datosTabla);
        JScrollPane barraTablaClientes = new JScrollPane(tablaClientes);
        barraTablaClientes.setBounds(10, 10, 300, 100);
        panelControlClientes.add(barraTablaClientes);
        
        JButton btnCargarArchivo = new JButton("Buscar archivo CSV");
        btnCargarArchivo.setBounds(350, 10, 200, 25);
        panelControlClientes.add(btnCargarArchivo);
    } 
}
