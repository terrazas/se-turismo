(import se.negocio.beans.*)
(import se.negocio.estrategias.*)

;PLANTILLAS

(deftemplate Usuario
    "Template para los datos del usuario"
    (declare (from-class Usuario))
)

(deftemplate Ciudad
    "Plantilla para los datos de las ciudades"
    (declare (from-class Ciudad))
)

(deftemplate Hospedaje
	"Plantilla para los datos de los hoteles"
	(declare (from-class Hospedaje))
)

(deftemplate Tour
	"Plantilla para los datos de los hoteles"
	(declare (from-class Tour))
)

;REGLAS

(defrule ciudad-de-origen
	"Disminuye el presupuesto del usuario dependiendo de su ciudad de origen."
    (declare (no-loop TRUE) (salience 10))
    (and
    ?usu<-(Usuario {presupuesto > 0})
    ?ciudad<-(Ciudad {nombre == usu.ciudadOrigen})
    )
	=>(retract ?ciudad)	
      (modify ?usu (presupuesto (- ?usu.presupuesto ?ciudad.costo)))
      (printout t "Ciudad-de-Origen: Costo de " ?ciudad.nombre " a cusco: " ?ciudad.costo crlf)
      (printout t "Presupuesto actual del usuario " ?usu.nombre " : " ?usu.presupuesto  crlf)
)

(defrule tipo-hospedaje 
	"Disminuye el presupuesto del usuario dependiendo del tipo de hospedaje que desee."
    (declare (no-loop TRUE) (salience 10))
    (and
    ?usu<-(Usuario {presupuesto > 0})
    ?hosp<-(Hospedaje {clasificacion == usu.tipoHospedaje})
    )
	=>(retract ?hosp)	
      (modify ?usu (presupuesto (- ?usu.presupuesto ?hosp.costo)))
      (printout t "Tipo-Hospedaje: Costo de hospedaje de " ?hosp.clasificacion " estrellas: " ?hosp.costo crlf)
      (printout t "Presupuesto actual del usuario " ?usu.nombre " : " ?usu.presupuesto  crlf)
)

(defrule analisis-preferencias 
	"Encuentra los tours que cumplan las preferencias del usuario."
    (declare (no-loop TRUE))
    (and
    ?usu<-(Usuario (preferencias $?usuprefs) (presupuesto ?presupuesto) {presupuesto > 0})
    ?tour<-(Tour (preferencias $?tourprefs) (costo ?costo))
    (test (eq (length$ ?usuprefs) (length$ (intersection$ ?tourprefs ?usuprefs))))
    (test (>= ?presupuesto ?costo))    
    )
	=>;(retract ?hosp)	
      (store codigo_tour ?tour.codigo)
      (printout t "Analisis-Preferencias: Segun las preferencias del usuario " ?usu.nombre " con preferencias " ?usuprefs " ")
      (printout t " y un presupuesto de " ?usu.presupuesto crlf)
      (printout t "se escogio el tour '" ?tour.nombre "' de costo " ?tour.costo " que satisface las preferencias " ?tourprefs crlf)
)


