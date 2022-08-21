package practicas2022;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ventana extends JFrame {

    usuario usuSistema[] = new usuario[10];
    JPanel panelInicioSesion = new JPanel();
    JPanel panelControl = new JPanel();
    JPanel panelCrearUsuario = new JPanel();

    public ventana() {
        objetos();
        crearAdmin();
    }

    public void crearAdmin() {
        usuSistema[0] = new usuario();
        usuSistema[0].nombreUsuario = "admin";
        usuSistema[0].nombre = "administrador";
        usuSistema[0].contra = "123456";
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
    public void panelControl(){
        this.getContentPane().add(panelControl);
        panelControl.setLayout(null);
        this.setSize(600, 500);
        this.setTitle("Control Principal");
        panelInicioSesion.setVisible(false);
        
        JButton btnAdminClientes = new JButton("Administración de Clientes");
        btnAdminClientes.setBounds(150, 10, 250, 25);
        panelControl.add(btnAdminClientes);
        
        JButton btnAdminProductos = new JButton("Administración de Productos");
        btnAdminProductos.setBounds(150, 80, 250, 25);
        panelControl.add(btnAdminProductos);
        
        JButton btnReportes = new JButton("Reportes");
        btnReportes.setBounds(150, 150, 250, 25);
        panelControl.add(btnReportes);
    }
    public void crearUsuario(){
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
        btnRegistrar.setBounds(130, 300, 200, 35);
        panelCrearUsuario.add(btnRegistrar);
        ActionListener registro = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(txtUsuario.getText().equals("") || txtNombreUsuario.getText().equals("") || txtContra.getText().equals("") || txtConfContra.getText().equals("")){
                   JOptionPane.showMessageDialog(null, "Debe de llenar todos los campos");
                }else{
                    if(txtContra.getText().equals(txtConfContra.getText())){
                        guardarUsuario();
                    }else{
                        JOptionPane.showMessageDialog(null, "Las contraseñas NO coinsiden");
                    }
                }
            }
        };
        btnRegistrar.addActionListener(registro);
    }
    public void guardarUsuario(){
        int posicion = 0;
        for(int i = 0; i < 9; i++){
            if(usuSistema[i] == null){
                posicion = i;
                break;
            }
        }
        System.out.println("La posicion libre es" + posicion);
    }
}
