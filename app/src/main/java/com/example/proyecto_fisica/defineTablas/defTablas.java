package com.example.proyecto_fisica.defineTablas;

public class defTablas {
    // Definición de nombres de tablas y columnas
    public static final String tUsuario = "usuarios";
    public static final String cCodigo = "codigo";
    public static final String cNombre = "nombre";
    public static final String cGrupo = "grupo";
    public static final String cBoleta = "boleta";
    public static final String cTurno = "turno";
    public static final String cCarrera = "carrera";
    public static final String cTelCel = "telcel";
    public static final String cEdad = "edad";
    public static final String cCorreo = "correo";
    public static final String cContra = "contrasena";

    // Definición de la consulta SQL para crear la tabla usuario
    public static final String crear_usuario = "CREATE TABLE " + tUsuario + " ("
            + cCodigo + " INTEGER PRIMARY KEY, "
            + cNombre + " TEXT, "
            + cBoleta + " TEXT, "
            + cGrupo + " TEXT, "
            + cTurno + " TEXT, "
            + cCarrera + " TEXT, "
            + cTelCel + " TEXT, "
            + cEdad + " TEXT, "
            + cCorreo + " TEXT, "
            + cContra + " TEXT);";

    // Definición de la consulta SQL para insertar datos en la tabla usuario
    public static final String insertar_usuario = "INSERT INTO " + tUsuario + " VALUES "
            + "(1, 'Ian Escobar', '2021080572', '6IV10', 'Vespertino', 'Computacion', '123456', '19', 'escobarianesp@gmail.com', '1234'), "
            + "(2, 'Miguel López', '2021080572', '6IV10', 'Vespertino', 'Computacion', '113344', '19', 'mikyloher@gmail.com', '1234'), "
            + "(3, 'Vanessa Gonzalez', '2021080572', '6IV10', 'Vespertino', 'Computacion', '556677', '19', 'vaneglz@gmail.com', '0707');";

    // Definición de la consulta SQL para eliminar la tabla usuarios
    public static final String eliminar_usuario = "DROP TABLE IF EXISTS " + tUsuario + ";";





    // Tabla preguntas
    public static final String tPreguntas = "preguntas";
    public static final String cIdPreg = "idPregunta";
    public static final String cUnidadAp = "unidadAp";
    public static final String cTema = "tema";
    public static final String cPregunta = "pregunta";
    public static final String cResp1 = "resp1";
    public static final String cResp2 = "resp2";
    public static final String cResp3 = "resp3";
    public static final String cResp4 = "resp4";
    public static final String cRespC = "respC";
    public static final String cImg = "cImg";

    // Definición de la consulta SQL para crear la tabla preguntas
    public static final String crear_pregunta = "CREATE TABLE " + tPreguntas + " ("
            + cIdPreg + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + cUnidadAp + " TEXT, "
            + cTema + " TEXT, "
            + cPregunta + " TEXT, "
            + cResp1 + " TEXT, "
            + cResp2 + " TEXT, "
            + cResp3 + " TEXT, "
            + cResp4 + " TEXT, "
            + cRespC + " INT, "
            + cImg + " TEXT);";

    // Definición de la consulta SQL para insertar datos en la tabla preguntas
    public static final String insertar_pregunta = "INSERT INTO " + tPreguntas + " ("
            + cUnidadAp + ", " + cPregunta + ", " + cResp1 + ", " + cResp2 + ", " + cResp3 + ", " + cResp4 + ", " + cRespC + ", " + cImg + ") VALUES "
            + "('FISICAIV', '¿Qué tipo de fragmentos se observaron por primera vez relacionados con el magnetismo?', 'Fragmentos de hierro', 'Fragmentos de cobre', 'Fragmentos de plástico', 'Fragmentos de vidrio', 1, 'icono1'), "
            + "('FISICAIV', '¿Qué es un imán?', 'Un material que repele objetos de hierro', 'Un material que atrae objetos de hierro', 'Un material que se disuelve en agua', 'Un material que flota en el aire', 2, 'icono1'), "
            + "('FISICAIV', '¿Qué son los polos magnéticos?', 'Las regiones sin fuerza magnética', 'Las regiones donde la fuerza magnética es más débil', 'Las regiones donde la fuerza magnética es más fuerte', 'Las regiones donde el magnetismo se invierte', 3, 'icono1'), "
            + "('FISICAIV', '¿Qué ocurre cuando se suspenden materiales magnéticos de un cordel?', 'Se alinean con el campo magnético de la Tierra', 'Se alinean al azar', 'Se repelen entre sí', 'Se atraen entre sí', 1, 'icono1'), "
            + "('FISICAIV', '¿Qué es la ley de la fuerza magnética?', 'Describe cómo los objetos caen', 'Describe cómo interactúan los polos magnéticos', 'Describe cómo los objetos flotan', 'Describe cómo los objetos se disuelven', 2, 'icono1'), "
            + "('FISICAIV', '¿Pueden existir polos aislados en un imán?', 'No, los imanes no tienen polos', 'Sí, pueden existir polos aislados', 'No, siempre existen en pares', 'Sí, si se cortan en pedazos pequeños', 3, 'icono1'), "
            + "('FISICAIV', '¿Qué rodea a todo imán?', 'Un campo magnético', 'Un campo eléctrico', 'Un campo gravitatorio', 'Un campo nuclear', 1, 'icono1'), "
            + "('FISICAIV', '¿Qué son las líneas de campo magnético?', 'Líneas que muestran la dirección de la corriente', 'Líneas que muestran la fuerza del campo eléctrico', 'Líneas que muestran la dirección del viento', 'Líneas que muestran la dirección del campo magnético', 4, 'icono1'), "
            + "('FISICAIV', '¿Cuál es la dirección de las líneas de flujo magnético?', 'De este a oeste', 'De sur a norte', 'De norte a sur', 'De oeste a este', 3, 'icono1'), "
            + "('FISICAIV', '¿Qué atribuye el magnetismo en la materia?', 'El movimiento de los protones en los átomos', 'El movimiento de los electrones en los átomos', 'El movimiento de los neutrones en los átomos', 'El movimiento de los núcleos atómicos', 2, 'icono1');";

    // Definición de la consulta SQL para eliminar la tabla preguntas
    public static final String eliminar_pregunta = "DROP TABLE IF EXISTS " + tPreguntas + ";";






    // Tabla cuestionario
    public static final String tCuestionario = "cuestionario";
    public static final String cIdCuestionario = "idCuestionario";
    public static final String cNombreC = "nombre";
    public static final String cDescripcion = "descripcion";

    // Definición de la consulta SQL para crear la tabla cuestionario
    public static final String crear_cuestionario = "CREATE TABLE " + tCuestionario + " ("
            + cIdCuestionario + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + cNombreC + " TEXT NOT NULL, "
            + cDescripcion + " TEXT DEFAULT NULL);";

    // Definición de la consulta SQL para insertar datos en la tabla cuestionario
    public static final String insertar_cuestionario = "INSERT INTO " + tCuestionario + " VALUES "
            + "(1, 'Cuestionario de Física IV', 'Preguntas sobre conceptos básicos de magnetismo');";

    // Definición de la consulta SQL para eliminar la tabla cuestionario
    public static final String eliminar_cuestionario = "DROP TABLE IF EXISTS " + tCuestionario + ";";




    // Tabla cuestionario_preguntas
    public static final String tCuestionarioPreguntas = "cuestionario_preguntas";
    public static final String cIdPreguntaCP = "idPregunta";
    public static final String cIdCuestionarioCP = "idCuestionario";

    // Definición de la consulta SQL para crear la tabla cuestionario_preguntas
    public static final String crear_cuestionario_preguntas = "CREATE TABLE " + tCuestionarioPreguntas + " ("
            + cIdCuestionarioCP + " INTEGER , "
            + cIdPreguntaCP + " INTEGER, "
            + "PRIMARY KEY (" + cIdCuestionarioCP + ", " + cIdPreguntaCP + "), "
            + "FOREIGN KEY (" + cIdCuestionarioCP + ") REFERENCES " + tCuestionario + " (" + cIdCuestionario + "), "
            + "FOREIGN KEY (" + cIdPreguntaCP + ") REFERENCES " + tPreguntas + " (" + cIdPreg + "));";

    // Definición de la consulta SQL para insertar datos en la tabla cuestionario_preguntas
    public static final String insertar_cuestionario_preguntas = "INSERT INTO " + tCuestionarioPreguntas + " VALUES "
            + "(1, 1), (1, 2), (1, 3), (1, 4), (1, 5), (1, 6), (1, 7), (1, 8), (1, 9), (1, 10);";

    // Definición de la consulta SQL para eliminar la tabla cuestionario_preguntas
    public static final String eliminar_cuestionario_preguntas = "DROP TABLE IF EXISTS " + tCuestionarioPreguntas + ";";




    // Tabla resultados
    public static final String tResultados = "resultados";
    public static final String cIdResultado = "idResultado";
    public static final String cIdCuestionarioR = "idCuestionario";
    public static final String cCodigoUsuario = "codigoUsuario";
    public static final String cPuntaje = "puntaje";
    public static final String cFecha = "fecha";

    // Definición de la consulta SQL para crear la tabla resultados
    public static final String crear_resultados = "CREATE TABLE " + tResultados + " ("
            + cIdResultado + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + cIdCuestionarioR + " INTEGER , "
            + cCodigoUsuario + " INTEGER , "
            + cPuntaje + " INTEGER, "
            + cFecha + " TEXT, "
            + "FOREIGN KEY (" + cIdCuestionarioR + ") REFERENCES " + tCuestionario + " ( " + cIdCuestionario + " ), "
            + "FOREIGN KEY (" + cCodigoUsuario + ") REFERENCES " + tUsuario + " ( " + cCodigo + " ));";

    // Definición de la consulta SQL para insertar datos en la tabla resultados
    public static final String insertar_resultados = "INSERT INTO " + tResultados + " VALUES "
            + "(1, 1, 1, 8, '2024-06-19'), "
            + "(2, 1, 1, 5, '2024-06-20'), "
            + "(3, 1, 1, 2, '2024-06-18'), "
            + "(4, 1, 1, 1, '2024-06-16'), "
            + "(5, 1, 1, 10, '2024-06-09'), "
            + "(6, 1, 2, 6, '2024-06-10'), "
            + "(7, 1, 1, 4, '2024-06-02'), "
            + "(8, 1, 1, 0, '2024-06-01'), "
            + "(9, 1, 1, 2, '2024-06-08'), "
            + "(10, 1, 1, 1, '2024-06-15'), "
            + "(11, 1, 1, 10, '2024-06-24'), "
            + "(12, 1, 2, 8, '2024-06-20'), "
            + "(13, 1, 2, 7, '2024-06-19');";


    public static final String consulta_resultados =
            "SELECT c." + cNombreC + " AS nombre_cuestionario, r." + cPuntaje + ", r." + cFecha +
                    " FROM " + tUsuario + " u" +
                    " JOIN " + tResultados + " r ON u." + cCodigo + " = r." + cCodigoUsuario +
                    " JOIN " + tCuestionario + " c ON r." + cIdCuestionario + " = c." + cIdCuestionario +
                    " WHERE u." + cCodigo + " = ?";
    // Definición de la consulta SQL para eliminar la tabla resultados
    public static final String eliminar_resultados = "DROP TABLE IF EXISTS " + tResultados + ";";


}
