package com.maishealth.maishealth.usuario.persistencia;

public class ConstantePopularBanco {
    public static final String INSERIR_USUARIO = "INSERT INTO `usuario` (`email`,`senha`) VALUES " +
            "('a@gmail.com','111111')," +
            "('b@gmail.com','111111')," +
            "('c@gmail.com','111111')," +
            "('d@gmail.com','111111')," +
            "('e@gmail.com','111111')," +
            "('f@gmail.com','111111')," +
            "('g@gmail.com','111111')," +
            "('h@gmail.com','111111')," +
            "('i@gmail.com','111111')," +
            "('j@gmail.com','111111')," +
            "('u@gmail.com','111111')," +
            "('v@gmail.com','111111');";

    public static final String INSERIR_PESSOA = "INSERT INTO `pessoa` (`nome`,`sexo`,`data_nasc`,`cpf`,`id_est_usuario`) VALUES " +
            "('Maite Stokes','Feminino','19500212','079.476.434-00',1)," +
            "('Kane Kramer','Masculino','19870515','079.476.434-01',2)," +
            "('Jenette Caldwell','Feminino','19881201','079.476.434-02',3)," +
            "('Nomlanga Dennis','Masculino','19900212','079.476.434-03',4)," +
            "('Leigh Day','Masculino','20001120','079.476.434-04',5)," +
            "('Laith Miller','Feminino','19850417','079.476.434-05',6)," +
            "('Amela Anderson','Feminino','19400123','079.476.434-06',7)," +
            "('Lucy Whitehead','Feminino','19730202','079.476.434-07',8)," +
            "('Indigo Ortega','Masculino','19870227','079.476.434-08',9)," +
            "('Akeem Stout','Feminino','19950505','079.476.434-09',10);";

    public static final String INSERIR_PACIENTE = "INSERT INTO `paciente` (`tipo_sangue`,`id_est_usuario`) VALUES " +
            "('A-',1)," +
            "('B+',2)," +
            "('O+',3)," +
            "('B+',4)," +
            "('A+',5)," +
            "('B-',6)," +
            "('A+',7)," +
            "('AB+',8)," +
            "('A-',9)," +
            "('O-',10);";

    public static final String INSERIR_MEDICO = "INSERT INTO `medico` (`crm`,`id_est_usuario`,`estado`,`especialidade`) VALUES " +
            "('111.100.',1,'PE','Clínico Geral')," +
            "('111.101.',2,'AC','Pediatria')," +
            "('111.102.',3,'RJ','Clínico Geral')," +
            "('111.103.',4,'SP','Cardiologia')," +
            "('111.104.',5,'BA','Cardiologia');";

    public static final String INSERIR_MEDICAMENTO = "INSERT INTO 'medicamento' ('nome') VALUES" +
            "('rivotril')," +
            "('Neosaldina')," +
            "('Dorflex')," +
            "('Diazepan')," +
            "('Lasartana');";

    public static final String INSERIR_HORARIO_MEDICO = "INSERT INTO `horario_medico` (`dia_da_semana`,`vagas`,`turno`,`id_est_medico`) VALUES " +
            "('Segunda',8,'Manhã',4)," +
            "('Segunda',7,'Tarde',4)," +
            "('Segunda',6,'Noite',4)," +
            "('Terça',5,'Manhã',4)," +
            "('Terça',6,'Tarde',4)," +
            "('Terça',4,'Noite',4)," +
            "('Quarta',1,'Manhã',4)," +
            "('Quarta',9,'Tarde',4)," +
            "('Quarta',2,'Noite',4)," +
            "('Quinta',4,'Tarde',4)," +
            "('Quinta',5,'Noite',4)," +
            "('Quinta',7,'Manhã',4)," +
            "('Sexta',6,'Manhã',4);" +
            "('Sexta',2,'Tarde',4);" +
            "('Sexta',3,'Noite',4);";

    public static final String INSERIR_POSTO = "INSERT INTO 'posto' ('nome','local') VALUES" +
            "('Posto Maria Lucinda','Rua A')," +
            "('Posto B','Rua B')," +
            "('Posto C','Rua C')," +
            "('Posto D','Rua D')," +
            "('Posto E','Rua E');";

    public static final String INSERIR_MEDICO_POSTO = "INSERT INTO 'medico_posto' ('id_medico','id_posto') VALUES" +
            "(1,1)," +
            "(2,1)," +
            "(3,1)," +
            "(4,1)," +
            "(5,1);";
    public static final String INSERIR_CONSULTA = "INSERT INTO 'consulta' " +
            "('id_medico','id_paciente','data','turno','status_consulta') VALUES " +
            "(4,0,'09/02/2018','Manhã','DISPONIVEL')," +
            "(4,0,'09/02/2018','Manhã','DISPONIVEL')," +
            "(4,0,'09/02/2018','Manhã','DISPONIVEL')," +
            "(4,0,'09/02/2018','Manhã','DISPONIVEL')," +
            "(4,8,'09/02/2018','Manhã','EMANDAMENTO')," +
            "(4,9,'09/02/2018','Manhã','EMANDAMENTO')," +
            "(4,10,'09/02/2018','Manhã','EMANDAMENTO')," +
            "(4,10,'10/02/2018','Manhã','EMANDAMENTO')," +
            "(5,10,'10/02/2018','Manhã','EMANDAMENTO')," +
            "(3,10,'11/02/2018','Manhã','EMANDAMENTO')," +
            "(4,7,'09/02/2018','Manhã','EMANDAMENTO');";

}
