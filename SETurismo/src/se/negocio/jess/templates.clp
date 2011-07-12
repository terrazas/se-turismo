(import se.negocio.beans.*)
(import se.negocio.estrategias.*)

(deftemplate Preferencias
    "Template para las preferencias del usuario"
    (declare (from-class Preferencias))
)

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