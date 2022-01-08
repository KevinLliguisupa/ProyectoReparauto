-- Database generated with pgModeler (PostgreSQL Database Modeler).
-- pgModeler  version: 0.9.2
-- PostgreSQL version: 10.0
-- Project Site: pgmodeler.io
-- Model Author: ---


-- Database creation must be done outside a multicommand file.
-- These commands were put in this file only as a convenience.
-- -- object: mipymes | type: DATABASE --
-- -- DROP DATABASE IF EXISTS mipymes;
-- CREATE DATABASE mipymes
-- 	ENCODING = 'UTF8'
-- 	LC_COLLATE = 'es_EC.UTF-8'
-- 	LC_CTYPE = 'es_EC.UTF-8'
-- 	TABLESPACE = pg_default;
-- -- ddl-end --
-- 

-- object: public.seg_usuario_id_seg_usuario_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.seg_usuario_id_seg_usuario_seq CASCADE;
CREATE SEQUENCE public.seg_usuario_id_seg_usuario_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --

-- object: public.seg_usuario | type: TABLE --
-- DROP TABLE IF EXISTS public.seg_usuario CASCADE;
CREATE TABLE public.seg_usuario (
	id_seg_usuario integer NOT NULL DEFAULT nextval('public.seg_usuario_id_seg_usuario_seq'::regclass),
	codigo character varying(10) NOT NULL,
	apellidos character varying(50) NOT NULL,
	nombres character varying(50) NOT NULL,
	correo character varying(50) NOT NULL,
	clave character varying(50) NOT NULL,
	activo boolean NOT NULL,
	CONSTRAINT seg_usuario_pk PRIMARY KEY (id_seg_usuario)

);
-- ddl-end --

-- object: public.seg_modulo_id_seg_modulo_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.seg_modulo_id_seg_modulo_seq CASCADE;
CREATE SEQUENCE public.seg_modulo_id_seg_modulo_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --

-- object: public.seg_modulo | type: TABLE --
-- DROP TABLE IF EXISTS public.seg_modulo CASCADE;
CREATE TABLE public.seg_modulo (
	id_seg_modulo integer NOT NULL DEFAULT nextval('public.seg_modulo_id_seg_modulo_seq'::regclass),
	nombre_modulo character varying(50) NOT NULL,
	icono character varying(100),
	CONSTRAINT seg_modulo_pk PRIMARY KEY (id_seg_modulo),
	CONSTRAINT uk_nombre_modulo UNIQUE (nombre_modulo)

);
-- ddl-end --

-- object: public.seg_asignacion_id_seg_asignacion_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.seg_asignacion_id_seg_asignacion_seq CASCADE;
CREATE SEQUENCE public.seg_asignacion_id_seg_asignacion_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --

-- object: public.seg_asignacion | type: TABLE --
-- DROP TABLE IF EXISTS public.seg_asignacion CASCADE;
CREATE TABLE public.seg_asignacion (
	id_seg_asignacion integer NOT NULL DEFAULT nextval('public.seg_asignacion_id_seg_asignacion_seq'::regclass),
	id_seg_usuario integer NOT NULL,
	id_seg_perfil integer NOT NULL,
	CONSTRAINT seg_asignacion_pk PRIMARY KEY (id_seg_asignacion),
	CONSTRAINT uk_asignacion UNIQUE (id_seg_usuario,id_seg_perfil)

);
-- ddl-end --

-- object: public.aud_bitacora_id_aud_bitacora_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.aud_bitacora_id_aud_bitacora_seq CASCADE;
CREATE SEQUENCE public.aud_bitacora_id_aud_bitacora_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --

-- object: public.aud_bitacora | type: TABLE --
-- DROP TABLE IF EXISTS public.aud_bitacora CASCADE;
CREATE TABLE public.aud_bitacora (
	id_aud_bitacora integer NOT NULL DEFAULT nextval('public.aud_bitacora_id_aud_bitacora_seq'::regclass),
	fecha_evento timestamp NOT NULL,
	nombre_clase character varying(100) NOT NULL,
	nombre_metodo character varying(100) NOT NULL,
	descripcion_evento character varying(300) NOT NULL,
	id_usuario character varying(100) NOT NULL,
	direccion_ip character varying(100) NOT NULL,
	CONSTRAINT aud_bitacora_pk PRIMARY KEY (id_aud_bitacora)

);
-- ddl-end --

-- object: public.thm_cargo_id_thm_cargo_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.thm_cargo_id_thm_cargo_seq CASCADE;
CREATE SEQUENCE public.thm_cargo_id_thm_cargo_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --

-- object: public.thm_cargo | type: TABLE --
-- DROP TABLE IF EXISTS public.thm_cargo CASCADE;
CREATE TABLE public.thm_cargo (
	id_thm_cargo integer NOT NULL DEFAULT nextval('public.thm_cargo_id_thm_cargo_seq'::regclass),
	nombre_cargo character varying(50) NOT NULL,
	remuneracion_mensual numeric(7,2) NOT NULL,
	CONSTRAINT thm_cargo_pk PRIMARY KEY (id_thm_cargo)

);
-- ddl-end --

-- object: public.thm_empleado_id_thm_empleado_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.thm_empleado_id_thm_empleado_seq CASCADE;
CREATE SEQUENCE public.thm_empleado_id_thm_empleado_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --

-- object: public.thm_empleado | type: TABLE --
-- DROP TABLE IF EXISTS public.thm_empleado CASCADE;
CREATE TABLE public.thm_empleado (
	id_thm_empleado integer NOT NULL DEFAULT nextval('public.thm_empleado_id_thm_empleado_seq'::regclass),
	id_thm_cargo integer NOT NULL,
	id_seg_usuario integer NOT NULL,
	horas_trabajadas smallint NOT NULL,
	horas_extra smallint NOT NULL,
	cuota_prestamo numeric(7,2) NOT NULL,
	CONSTRAINT thm_empleado_pk PRIMARY KEY (id_thm_empleado),
	CONSTRAINT uk_empleado_usuario UNIQUE (id_seg_usuario)

);
-- ddl-end --

-- object: public.thm_rol_cabecera_id_thm_rol_cabecera_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.thm_rol_cabecera_id_thm_rol_cabecera_seq CASCADE;
CREATE SEQUENCE public.thm_rol_cabecera_id_thm_rol_cabecera_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --

-- object: public.thm_rol_cabecera | type: TABLE --
-- DROP TABLE IF EXISTS public.thm_rol_cabecera CASCADE;
CREATE TABLE public.thm_rol_cabecera (
	id_thm_rol_cabecera integer NOT NULL DEFAULT nextval('public.thm_rol_cabecera_id_thm_rol_cabecera_seq'::regclass),
	id_thm_empleado integer NOT NULL,
	periodo_rol character varying(6) NOT NULL,
	nombre_cargo character varying(50) NOT NULL,
	horas_trabajadas smallint NOT NULL,
	horas_extras smallint NOT NULL,
	subtotal_ingresos numeric(7,2) NOT NULL,
	subtotal_ingresos_adicionales numeric(7,2) NOT NULL,
	subtotal_egresos numeric(7,2) NOT NULL,
	total numeric(7,2) NOT NULL,
	CONSTRAINT thm_rol_cabecera_pkey PRIMARY KEY (id_thm_rol_cabecera)

);
-- ddl-end --

-- object: public.thm_rol_detalle_id_thm_rol_detalle_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.thm_rol_detalle_id_thm_rol_detalle_seq CASCADE;
CREATE SEQUENCE public.thm_rol_detalle_id_thm_rol_detalle_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --

-- object: public.thm_rol_detalle | type: TABLE --
-- DROP TABLE IF EXISTS public.thm_rol_detalle CASCADE;
CREATE TABLE public.thm_rol_detalle (
	id_thm_rol_detalle integer NOT NULL DEFAULT nextval('public.thm_rol_detalle_id_thm_rol_detalle_seq'::regclass),
	id_thm_rol_cabecera integer NOT NULL,
	tipo_detalle character varying(2) NOT NULL,
	descripcion character varying(100) NOT NULL,
	valor numeric(7,2) NOT NULL,
	orden smallint NOT NULL,
	CONSTRAINT thm_rol_detalle_pkey PRIMARY KEY (id_thm_rol_detalle)

);
-- ddl-end --

-- object: public.vw_thm_consulta_rol | type: VIEW --
-- DROP VIEW IF EXISTS public.vw_thm_consulta_rol CASCADE;
CREATE VIEW public.vw_thm_consulta_rol
AS 

SELECT trc.id_thm_rol_cabecera,
    trc.periodo_rol,
    trc.id_thm_empleado,
    trc.total,
    te.horas_extra,
    su.apellidos
   FROM thm_rol_cabecera trc,
    thm_empleado te,
    seg_usuario su
  WHERE ((trc.id_thm_empleado = te.id_thm_empleado) AND (te.id_seg_usuario = su.id_seg_usuario));
-- ddl-end --

-- object: public.pry_proyecto_id_pry_proyecto_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.pry_proyecto_id_pry_proyecto_seq CASCADE;
CREATE SEQUENCE public.pry_proyecto_id_pry_proyecto_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --

-- object: public.pry_proyecto | type: TABLE --
-- DROP TABLE IF EXISTS public.pry_proyecto CASCADE;
CREATE TABLE public.pry_proyecto (
	id_pry_proyecto integer NOT NULL DEFAULT nextval('public.pry_proyecto_id_pry_proyecto_seq'::regclass),
	nombre character varying(100) NOT NULL,
	fecha_inicio date NOT NULL,
	fecha_fin date NOT NULL,
	estado character varying(1) NOT NULL,
	avance smallint NOT NULL,
	CONSTRAINT pry_proyecto_pkey PRIMARY KEY (id_pry_proyecto)

);
-- ddl-end --

-- object: public.pry_tarea_id_pry_tarea_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.pry_tarea_id_pry_tarea_seq CASCADE;
CREATE SEQUENCE public.pry_tarea_id_pry_tarea_seq
	INCREMENT BY 1
	MINVALUE 1
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --

-- object: public.pry_tarea | type: TABLE --
-- DROP TABLE IF EXISTS public.pry_tarea CASCADE;
CREATE TABLE public.pry_tarea (
	id_pry_tarea integer NOT NULL DEFAULT nextval('public.pry_tarea_id_pry_tarea_seq'::regclass),
	nombre character varying(100) NOT NULL,
	fecha_inicio date NOT NULL,
	fecha_fin date NOT NULL,
	avance smallint NOT NULL,
	id_seg_usuario integer NOT NULL,
	id_pry_proyecto integer,
	CONSTRAINT pry_tarea_pkey PRIMARY KEY (id_pry_tarea)

);
-- ddl-end --

-- object: public.seg_perfil | type: TABLE --
-- DROP TABLE IF EXISTS public.seg_perfil CASCADE;
CREATE TABLE public.seg_perfil (
	id_seg_perfil serial NOT NULL,
	nombre_perfil character varying(50) NOT NULL,
	ruta_acceso character varying(100) NOT NULL,
	id_seg_modulo integer NOT NULL,
	CONSTRAINT seg_perfil_pk PRIMARY KEY (id_seg_perfil)

);
-- ddl-end --

-- object: public.inv_material_id_inv_material_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.inv_material_id_inv_material_seq CASCADE;
CREATE SEQUENCE public.inv_material_id_inv_material_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.inv_material_id_inv_material_seq OWNER TO postgres;
-- ddl-end --

-- object: public.inv_material | type: TABLE --
-- DROP TABLE IF EXISTS public.inv_material CASCADE;
CREATE TABLE public.inv_material (
	mat_id integer NOT NULL DEFAULT nextval('public.inv_material_id_inv_material_seq'::regclass),
	mat_nombre character varying(50),
	mat_precio_venta numeric(7,2),
	mat_existencia numeric(6),
	mat_unidad_medida character varying(10),
	mat_estado boolean,
	mat_imagen character varying,
	tip_id integer,
	CONSTRAINT inv_material_pk PRIMARY KEY (mat_id)

);
-- ddl-end --
-- ALTER TABLE public.inv_material OWNER TO postgres;
-- ddl-end --

-- object: public.inv_tipo_id_inv_tipo_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.inv_tipo_id_inv_tipo_seq CASCADE;
CREATE SEQUENCE public.inv_tipo_id_inv_tipo_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.inv_tipo_id_inv_tipo_seq OWNER TO postgres;
-- ddl-end --

-- object: public.inv_tipo | type: TABLE --
-- DROP TABLE IF EXISTS public.inv_tipo CASCADE;
CREATE TABLE public.inv_tipo (
	tip_id integer NOT NULL DEFAULT nextval('public.inv_tipo_id_inv_tipo_seq'::regclass),
	tip_nombre character varying(25),
	tip_estado boolean,
	CONSTRAINT inv_tipo_pk PRIMARY KEY (tip_id)

);
-- ddl-end --
-- ALTER TABLE public.inv_tipo OWNER TO postgres;
-- ddl-end --

-- object: public.inv_proveedor_id_inv_proveedor_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.inv_proveedor_id_inv_proveedor_seq CASCADE;
CREATE SEQUENCE public.inv_proveedor_id_inv_proveedor_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.inv_proveedor_id_inv_proveedor_seq OWNER TO postgres;
-- ddl-end --

-- object: public.inv_vehiculo_id_inv_vehiculo_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.inv_vehiculo_id_inv_vehiculo_seq CASCADE;
CREATE SEQUENCE public.inv_vehiculo_id_inv_vehiculo_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.inv_vehiculo_id_inv_vehiculo_seq OWNER TO postgres;
-- ddl-end --

-- object: public.inv_proveedor | type: TABLE --
-- DROP TABLE IF EXISTS public.inv_proveedor CASCADE;
CREATE TABLE public.inv_proveedor (
	pro_id integer NOT NULL DEFAULT nextval('public.inv_proveedor_id_inv_proveedor_seq'::regclass),
	pro_nombre character varying(50),
	pro_telefono character varying(10),
	pro_correo character varying(30),
	pro_estado boolean,
	CONSTRAINT inv_proveedor_pk PRIMARY KEY (pro_id)

);
-- ddl-end --
-- ALTER TABLE public.inv_proveedor OWNER TO postgres;
-- ddl-end --

-- object: public.rec_vehiculos | type: TABLE --
-- DROP TABLE IF EXISTS public.rec_vehiculos CASCADE;
CREATE TABLE public.rec_vehiculos (
	veh_id integer NOT NULL DEFAULT nextval('public.inv_vehiculo_id_inv_vehiculo_seq'::regclass),
	veh_placa character varying(20),
	veh_marca character varying(20),
	veh_color character varying(20),
	veh_anio character varying(5),
	veh_clave character varying(10),
	veh_numero_chasis character varying(10),
	veh_motor character varying(20),
	veh_kilometraje int4,
	veh_nivel_combustible character varying,
	veh_estado boolean,
	CONSTRAINT inv_vehiculos_pk PRIMARY KEY (veh_id)

);
-- ddl-end --
-- ALTER TABLE public.rec_vehiculos OWNER TO postgres;
-- ddl-end --

-- object: public.inv_salida_id_inv_salida_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.inv_salida_id_inv_salida_seq CASCADE;
CREATE SEQUENCE public.inv_salida_id_inv_salida_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.inv_salida_id_inv_salida_seq OWNER TO postgres;
-- ddl-end --

-- object: public.inv_salida | type: TABLE --
-- DROP TABLE IF EXISTS public.inv_salida CASCADE;
CREATE TABLE public.inv_salida (
	sal_id integer NOT NULL DEFAULT nextval('public.inv_salida_id_inv_salida_seq'::regclass),
	sal_fecha date,
	veh_id_rec_vehiculos integer,
	id_thm_empleado_thm_empleado integer,
	CONSTRAINT inv_salida_pk PRIMARY KEY (sal_id)

);
-- ddl-end --
-- ALTER TABLE public.inv_salida OWNER TO postgres;
-- ddl-end --

-- object: public.inv_ingreso_id_inv_ingreso_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.inv_ingreso_id_inv_ingreso_seq CASCADE;
CREATE SEQUENCE public.inv_ingreso_id_inv_ingreso_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.inv_ingreso_id_inv_ingreso_seq OWNER TO postgres;
-- ddl-end --

-- object: public.inv_ingreso | type: TABLE --
-- DROP TABLE IF EXISTS public.inv_ingreso CASCADE;
CREATE TABLE public.inv_ingreso (
	ing_id integer NOT NULL DEFAULT nextval('public.inv_ingreso_id_inv_ingreso_seq'::regclass),
	ing_fecha date,
	pro_id integer,
	CONSTRAINT inv_ingresos_pk PRIMARY KEY (ing_id)

);
-- ddl-end --
-- ALTER TABLE public.inv_ingreso OWNER TO postgres;
-- ddl-end --

-- object: public.inv_matsal_id_inv_matsal_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.inv_matsal_id_inv_matsal_seq CASCADE;
CREATE SEQUENCE public.inv_matsal_id_inv_matsal_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.inv_matsal_id_inv_matsal_seq OWNER TO postgres;
-- ddl-end --

-- object: public.inv_material_salida | type: TABLE --
-- DROP TABLE IF EXISTS public.inv_material_salida CASCADE;
CREATE TABLE public.inv_material_salida (
	mat_sal_id integer NOT NULL DEFAULT nextval('public.inv_matsal_id_inv_matsal_seq'::regclass),
	mat_id integer,
	sal_id integer,
	mat_sal_cantidad numeric(6),
	mat_sal_precio numeric(7,2),
	mat_sal_estado boolean,
	CONSTRAINT inv_mat_sal_pk PRIMARY KEY (mat_sal_id)

);
-- ddl-end --
-- ALTER TABLE public.inv_material_salida OWNER TO postgres;
-- ddl-end --

-- object: public.inv_mating_id_inv_mating_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.inv_mating_id_inv_mating_seq CASCADE;
CREATE SEQUENCE public.inv_mating_id_inv_mating_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.inv_mating_id_inv_mating_seq OWNER TO postgres;
-- ddl-end --

-- object: public.inv_material_ingreso | type: TABLE --
-- DROP TABLE IF EXISTS public.inv_material_ingreso CASCADE;
CREATE TABLE public.inv_material_ingreso (
	mat_ent_id integer NOT NULL DEFAULT nextval('public.inv_mating_id_inv_mating_seq'::regclass),
	mat_id integer,
	ing_id integer,
	mat_ing_cantidad numeric(6),
	mat_ing_precio_compra numeric(7,2),
	mat_ing_estado boolean,
	CONSTRAINT inv_mat_ent_pk PRIMARY KEY (mat_ent_id)

);
-- ddl-end --
-- ALTER TABLE public.inv_material_ingreso OWNER TO postgres;
-- ddl-end --

-- object: public.rec_vehiculo_id_rec_vehiculo_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.rec_vehiculo_id_rec_vehiculo_seq CASCADE;
CREATE SEQUENCE public.rec_vehiculo_id_rec_vehiculo_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.rec_vehiculo_id_rec_vehiculo_seq OWNER TO postgres;
-- ddl-end --

-- object: public.rec_vehiculo_extras | type: TABLE --
-- DROP TABLE IF EXISTS public.rec_vehiculo_extras CASCADE;
CREATE TABLE public.rec_vehiculo_extras (
	veh_ext_id integer NOT NULL DEFAULT nextval('public.rec_vehiculo_id_rec_vehiculo_seq'::regclass),
	veh_ext_encendor boolean,
	veh_ext_matricula_documento boolean,
	veh_ext_radio boolean,
	veh_ext_llavero boolean,
	veh_ext_control_alarma boolean,
	veh_ext_bateria boolean,
	veh_ext_llave_ruedas boolean,
	veh_ext_llanta_emergencia boolean,
	veh_ext_herramientas_basicas boolean,
	veh_ext_extintor boolean,
	veh_ext_triangulos boolean,
	veh_ext_cables_corriente boolean,
	veh_ext_mascarilla_radio boolean,
	veh_ext_antena boolean,
	veh_ext_num_ruedas float4,
	veh_ext_tapacubos int2,
	veh_ext_num_limpia_parabrisas int2,
	veh_ext_num_retrovisores int2,
	veh_ext_usb boolean,
	veh_ext_adicionales character varying(200),
	veh_ext_estado boolean,
	veh_id_rec_vehiculos integer,
	CONSTRAINT rec_vehiculo_extras_pk PRIMARY KEY (veh_ext_id)

);
-- ddl-end --
-- ALTER TABLE public.rec_vehiculo_extras OWNER TO postgres;
-- ddl-end --

-- object: public.rec_cliente_id_rec_cliente_seq | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.rec_cliente_id_rec_cliente_seq CASCADE;
CREATE SEQUENCE public.rec_cliente_id_rec_cliente_seq
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.rec_cliente_id_rec_cliente_seq OWNER TO postgres;
-- ddl-end --

-- object: public.rec_cliente | type: TABLE --
-- DROP TABLE IF EXISTS public.rec_cliente CASCADE;
CREATE TABLE public.rec_cliente (
	cli_id integer NOT NULL DEFAULT nextval('public.rec_cliente_id_rec_cliente_seq'::regclass),
	cli_nombre_apellido character varying(600),
	cli_cedula character varying(10),
	cli_celular character varying(10),
	cli_direccion character varying(50),
	cli_correo character varying(30),
	cli_telefono character varying(10),
	cli_estado boolean,
	CONSTRAINT rec_cliente_pk PRIMARY KEY (cli_id)

);
-- ddl-end --
-- ALTER TABLE public.rec_cliente OWNER TO postgres;
-- ddl-end --

-- object: public.rec_recepcion_detalle_id_recepcion_detalle | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.rec_recepcion_detalle_id_recepcion_detalle CASCADE;
CREATE SEQUENCE public.rec_recepcion_detalle_id_recepcion_detalle
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.rec_recepcion_detalle_id_recepcion_detalle OWNER TO postgres;
-- ddl-end --

-- object: public.rec_recepcion_detalle | type: TABLE --
-- DROP TABLE IF EXISTS public.rec_recepcion_detalle CASCADE;
CREATE TABLE public.rec_recepcion_detalle (
	rec_det_id integer NOT NULL DEFAULT nextval('public.rec_recepcion_detalle_id_recepcion_detalle'::regclass),
	rec_det_valor numeric(7,2),
	rec_ser_id_rec_servicio integer,
	"rec-det_horas_empleadas" int2,
	rec_det_concluido boolean,
	rec_det_servicio_extra boolean,
	rec_det_estado boolean,
	rec_cab_id_rec_recepcion_cabecera integer,
	id_thm_empleado_thm_empleado integer,
	CONSTRAINT rec_recepcion_detalle_pk PRIMARY KEY (rec_det_id)

);
-- ddl-end --
-- ALTER TABLE public.rec_recepcion_detalle OWNER TO postgres;
-- ddl-end --

-- object: public.rec_servicio_id_rec_servicio | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.rec_servicio_id_rec_servicio CASCADE;
CREATE SEQUENCE public.rec_servicio_id_rec_servicio
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.rec_servicio_id_rec_servicio OWNER TO postgres;
-- ddl-end --

-- object: public.rec_servicio | type: TABLE --
-- DROP TABLE IF EXISTS public.rec_servicio CASCADE;
CREATE TABLE public.rec_servicio (
	rec_ser_id integer NOT NULL DEFAULT nextval('public.rec_servicio_id_rec_servicio'::regclass),
	rec_ser_nombre character varying,
	rec_ser_precio numeric(7,2),
	rec_ser_estado boolean,
	CONSTRAINT rec_servicio_pk PRIMARY KEY (rec_ser_id)

);
-- ddl-end --
-- ALTER TABLE public.rec_servicio OWNER TO postgres;
-- ddl-end --

-- object: rec_servicio_fk | type: CONSTRAINT --
-- ALTER TABLE public.rec_recepcion_detalle DROP CONSTRAINT IF EXISTS rec_servicio_fk CASCADE;
ALTER TABLE public.rec_recepcion_detalle ADD CONSTRAINT rec_servicio_fk FOREIGN KEY (rec_ser_id_rec_servicio)
REFERENCES public.rec_servicio (rec_ser_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.rec_recepcion_cabecera_id_recepcion_cabecera | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.rec_recepcion_cabecera_id_recepcion_cabecera CASCADE;
CREATE SEQUENCE public.rec_recepcion_cabecera_id_recepcion_cabecera
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.rec_recepcion_cabecera_id_recepcion_cabecera OWNER TO postgres;
-- ddl-end --

-- object: public.rec_recepcion_cabecera | type: TABLE --
-- DROP TABLE IF EXISTS public.rec_recepcion_cabecera CASCADE;
CREATE TABLE public.rec_recepcion_cabecera (
	rec_cab_id integer NOT NULL DEFAULT nextval('public.rec_recepcion_cabecera_id_recepcion_cabecera'::regclass),
	rec_cab_fecha_recepcion date,
	rec_cab_fecha_entrega date,
	rec_cab_hora time,
	rec_cab_observacion character varying(200),
	rec_cab_total numeric(7,2),
	rec_cab_abono numeric(7,2),
	rec_cab_saldo numeric(7,2),
	veh_id_rec_vehiculos integer,
	rec_cab_estado boolean,
	cli_id_rec_cliente integer,
	id_seg_usuario_seg_usuario integer,
	CONSTRAINT rec_recepcion_cabecera_pk PRIMARY KEY (rec_cab_id)

);
-- ddl-end --
-- ALTER TABLE public.rec_recepcion_cabecera OWNER TO postgres;
-- ddl-end --

-- object: rec_vehiculos_fk | type: CONSTRAINT --
-- ALTER TABLE public.rec_recepcion_cabecera DROP CONSTRAINT IF EXISTS rec_vehiculos_fk CASCADE;
ALTER TABLE public.rec_recepcion_cabecera ADD CONSTRAINT rec_vehiculos_fk FOREIGN KEY (veh_id_rec_vehiculos)
REFERENCES public.rec_vehiculos (veh_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: rec_recepcion_cabecera_fk | type: CONSTRAINT --
-- ALTER TABLE public.rec_recepcion_detalle DROP CONSTRAINT IF EXISTS rec_recepcion_cabecera_fk CASCADE;
ALTER TABLE public.rec_recepcion_detalle ADD CONSTRAINT rec_recepcion_cabecera_fk FOREIGN KEY (rec_cab_id_rec_recepcion_cabecera)
REFERENCES public.rec_recepcion_cabecera (rec_cab_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: rec_vehiculos_fk | type: CONSTRAINT --
-- ALTER TABLE public.rec_vehiculo_extras DROP CONSTRAINT IF EXISTS rec_vehiculos_fk CASCADE;
ALTER TABLE public.rec_vehiculo_extras ADD CONSTRAINT rec_vehiculos_fk FOREIGN KEY (veh_id_rec_vehiculos)
REFERENCES public.rec_vehiculos (veh_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: rec_vehiculo_extras_uq | type: CONSTRAINT --
-- ALTER TABLE public.rec_vehiculo_extras DROP CONSTRAINT IF EXISTS rec_vehiculo_extras_uq CASCADE;
ALTER TABLE public.rec_vehiculo_extras ADD CONSTRAINT rec_vehiculo_extras_uq UNIQUE (veh_id_rec_vehiculos);
-- ddl-end --

-- object: rec_cliente_fk | type: CONSTRAINT --
-- ALTER TABLE public.rec_recepcion_cabecera DROP CONSTRAINT IF EXISTS rec_cliente_fk CASCADE;
ALTER TABLE public.rec_recepcion_cabecera ADD CONSTRAINT rec_cliente_fk FOREIGN KEY (cli_id_rec_cliente)
REFERENCES public.rec_cliente (cli_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: seg_usuario_fk | type: CONSTRAINT --
-- ALTER TABLE public.rec_recepcion_cabecera DROP CONSTRAINT IF EXISTS seg_usuario_fk CASCADE;
ALTER TABLE public.rec_recepcion_cabecera ADD CONSTRAINT seg_usuario_fk FOREIGN KEY (id_seg_usuario_seg_usuario)
REFERENCES public.seg_usuario (id_seg_usuario) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: rec_vehiculos_fk | type: CONSTRAINT --
-- ALTER TABLE public.inv_salida DROP CONSTRAINT IF EXISTS rec_vehiculos_fk CASCADE;
ALTER TABLE public.inv_salida ADD CONSTRAINT rec_vehiculos_fk FOREIGN KEY (veh_id_rec_vehiculos)
REFERENCES public.rec_vehiculos (veh_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: thm_empleado_fk | type: CONSTRAINT --
-- ALTER TABLE public.inv_salida DROP CONSTRAINT IF EXISTS thm_empleado_fk CASCADE;
ALTER TABLE public.inv_salida ADD CONSTRAINT thm_empleado_fk FOREIGN KEY (id_thm_empleado_thm_empleado)
REFERENCES public.thm_empleado (id_thm_empleado) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: thm_empleado_fk | type: CONSTRAINT --
-- ALTER TABLE public.rec_recepcion_detalle DROP CONSTRAINT IF EXISTS thm_empleado_fk CASCADE;
ALTER TABLE public.rec_recepcion_detalle ADD CONSTRAINT thm_empleado_fk FOREIGN KEY (id_thm_empleado_thm_empleado)
REFERENCES public.thm_empleado (id_thm_empleado) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: public.pro_not_id_pro_not | type: SEQUENCE --
-- DROP SEQUENCE IF EXISTS public.pro_not_id_pro_not CASCADE;
CREATE SEQUENCE public.pro_not_id_pro_not
	INCREMENT BY 1
	MINVALUE 0
	MAXVALUE 2147483647
	START WITH 1
	CACHE 1
	NO CYCLE
	OWNED BY NONE;
-- ddl-end --
-- ALTER SEQUENCE public.pro_not_id_pro_not OWNER TO postgres;
-- ddl-end --

-- object: public.pro_nota_venta | type: TABLE --
-- DROP TABLE IF EXISTS public.pro_nota_venta CASCADE;
CREATE TABLE public.pro_nota_venta (
	pro_not_id integer NOT NULL DEFAULT nextval('public.pro_not_id_pro_not'::regclass),
	rec_cab_id_rec_recepcion_cabecera integer,
	pro_not_total numeric(7,2),
	pro_not_fecha date,
	CONSTRAINT pro_nota_venta_pk PRIMARY KEY (pro_not_id)

);
-- ddl-end --
-- ALTER TABLE public.pro_nota_venta OWNER TO postgres;
-- ddl-end --

-- object: rec_recepcion_cabecera_fk | type: CONSTRAINT --
-- ALTER TABLE public.pro_nota_venta DROP CONSTRAINT IF EXISTS rec_recepcion_cabecera_fk CASCADE;
ALTER TABLE public.pro_nota_venta ADD CONSTRAINT rec_recepcion_cabecera_fk FOREIGN KEY (rec_cab_id_rec_recepcion_cabecera)
REFERENCES public.rec_recepcion_cabecera (rec_cab_id) MATCH FULL
ON DELETE SET NULL ON UPDATE CASCADE;
-- ddl-end --

-- object: pro_nota_venta_uq | type: CONSTRAINT --
-- ALTER TABLE public.pro_nota_venta DROP CONSTRAINT IF EXISTS pro_nota_venta_uq CASCADE;
ALTER TABLE public.pro_nota_venta ADD CONSTRAINT pro_nota_venta_uq UNIQUE (rec_cab_id_rec_recepcion_cabecera);
-- ddl-end --

-- object: seg_perfil_seg_asignacion_fk | type: CONSTRAINT --
-- ALTER TABLE public.seg_asignacion DROP CONSTRAINT IF EXISTS seg_perfil_seg_asignacion_fk CASCADE;
ALTER TABLE public.seg_asignacion ADD CONSTRAINT seg_perfil_seg_asignacion_fk FOREIGN KEY (id_seg_perfil)
REFERENCES public.seg_perfil (id_seg_perfil) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: seg_usuario_seg_asignacion_fk | type: CONSTRAINT --
-- ALTER TABLE public.seg_asignacion DROP CONSTRAINT IF EXISTS seg_usuario_seg_asignacion_fk CASCADE;
ALTER TABLE public.seg_asignacion ADD CONSTRAINT seg_usuario_seg_asignacion_fk FOREIGN KEY (id_seg_usuario)
REFERENCES public.seg_usuario (id_seg_usuario) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_empleado_cargo | type: CONSTRAINT --
-- ALTER TABLE public.thm_empleado DROP CONSTRAINT IF EXISTS fk_empleado_cargo CASCADE;
ALTER TABLE public.thm_empleado ADD CONSTRAINT fk_empleado_cargo FOREIGN KEY (id_thm_cargo)
REFERENCES public.thm_cargo (id_thm_cargo) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_empleado_usuario | type: CONSTRAINT --
-- ALTER TABLE public.thm_empleado DROP CONSTRAINT IF EXISTS fk_empleado_usuario CASCADE;
ALTER TABLE public.thm_empleado ADD CONSTRAINT fk_empleado_usuario FOREIGN KEY (id_seg_usuario)
REFERENCES public.seg_usuario (id_seg_usuario) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_cab_empleado | type: CONSTRAINT --
-- ALTER TABLE public.thm_rol_cabecera DROP CONSTRAINT IF EXISTS fk_cab_empleado CASCADE;
ALTER TABLE public.thm_rol_cabecera ADD CONSTRAINT fk_cab_empleado FOREIGN KEY (id_thm_empleado)
REFERENCES public.thm_empleado (id_thm_empleado) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_det_cab_rol | type: CONSTRAINT --
-- ALTER TABLE public.thm_rol_detalle DROP CONSTRAINT IF EXISTS fk_det_cab_rol CASCADE;
ALTER TABLE public.thm_rol_detalle ADD CONSTRAINT fk_det_cab_rol FOREIGN KEY (id_thm_rol_cabecera)
REFERENCES public.thm_rol_cabecera (id_thm_rol_cabecera) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: pry_tarea_id_seg_usuario_fkey | type: CONSTRAINT --
-- ALTER TABLE public.pry_tarea DROP CONSTRAINT IF EXISTS pry_tarea_id_seg_usuario_fkey CASCADE;
ALTER TABLE public.pry_tarea ADD CONSTRAINT pry_tarea_id_seg_usuario_fkey FOREIGN KEY (id_seg_usuario)
REFERENCES public.seg_usuario (id_seg_usuario) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_tarea_proyecto | type: CONSTRAINT --
-- ALTER TABLE public.pry_tarea DROP CONSTRAINT IF EXISTS fk_tarea_proyecto CASCADE;
ALTER TABLE public.pry_tarea ADD CONSTRAINT fk_tarea_proyecto FOREIGN KEY (id_pry_proyecto)
REFERENCES public.pry_proyecto (id_pry_proyecto) MATCH SIMPLE
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: fk_perfil_modulo | type: CONSTRAINT --
-- ALTER TABLE public.seg_perfil DROP CONSTRAINT IF EXISTS fk_perfil_modulo CASCADE;
ALTER TABLE public.seg_perfil ADD CONSTRAINT fk_perfil_modulo FOREIGN KEY (id_seg_modulo)
REFERENCES public.seg_modulo (id_seg_modulo) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: rel_inv_tipo_inv_material | type: CONSTRAINT --
-- ALTER TABLE public.inv_material DROP CONSTRAINT IF EXISTS rel_inv_tipo_inv_material CASCADE;
ALTER TABLE public.inv_material ADD CONSTRAINT rel_inv_tipo_inv_material FOREIGN KEY (tip_id)
REFERENCES public.inv_tipo (tip_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: rel_inv_proveedor_inv_ingresos | type: CONSTRAINT --
-- ALTER TABLE public.inv_ingreso DROP CONSTRAINT IF EXISTS rel_inv_proveedor_inv_ingresos CASCADE;
ALTER TABLE public.inv_ingreso ADD CONSTRAINT rel_inv_proveedor_inv_ingresos FOREIGN KEY (pro_id)
REFERENCES public.inv_proveedor (pro_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: rel_inv_material_inv_matsal | type: CONSTRAINT --
-- ALTER TABLE public.inv_material_salida DROP CONSTRAINT IF EXISTS rel_inv_material_inv_matsal CASCADE;
ALTER TABLE public.inv_material_salida ADD CONSTRAINT rel_inv_material_inv_matsal FOREIGN KEY (mat_id)
REFERENCES public.inv_material (mat_id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: rel_inv_salida_inv_matsal | type: CONSTRAINT --
-- ALTER TABLE public.inv_material_salida DROP CONSTRAINT IF EXISTS rel_inv_salida_inv_matsal CASCADE;
ALTER TABLE public.inv_material_salida ADD CONSTRAINT rel_inv_salida_inv_matsal FOREIGN KEY (sal_id)
REFERENCES public.inv_salida (sal_id) MATCH FULL
ON DELETE RESTRICT ON UPDATE CASCADE;
-- ddl-end --

-- object: rel_inv_material_inv_ingreso | type: CONSTRAINT --
-- ALTER TABLE public.inv_material_ingreso DROP CONSTRAINT IF EXISTS rel_inv_material_inv_ingreso CASCADE;
ALTER TABLE public.inv_material_ingreso ADD CONSTRAINT rel_inv_material_inv_ingreso FOREIGN KEY (mat_id)
REFERENCES public.inv_material (mat_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --

-- object: rel_inv_ingresos_inv_mating | type: CONSTRAINT --
-- ALTER TABLE public.inv_material_ingreso DROP CONSTRAINT IF EXISTS rel_inv_ingresos_inv_mating CASCADE;
ALTER TABLE public.inv_material_ingreso ADD CONSTRAINT rel_inv_ingresos_inv_mating FOREIGN KEY (ing_id)
REFERENCES public.inv_ingreso (ing_id) MATCH FULL
ON DELETE NO ACTION ON UPDATE NO ACTION;
-- ddl-end --


