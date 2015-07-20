package fp.grados.tipos;

import fp.grados.excepciones.ExcepcionEspacioNoValido;
import fp.grados.tipos.Espacio;
import fp.grados.tipos.TipoEspacio;

public class EspacioImpl implements Espacio {
	
	private TipoEspacio tipo;
	private String nombre;
	private Integer planta;
	private Integer capacidad;


	public EspacioImpl(TipoEspacio tipo, String nombre, Integer capacidad, Integer planta) {

		this.tipo = tipo;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.planta = planta;
		checkCapacidad(capacidad);
	}

	public EspacioImpl(String s) {
		String [] e = s.split(",");
		if(e.length != 4) {
			throw new IllegalArgumentException();
		}
		
		nombre = e[0].trim();
		planta = Integer.parseInt(e[1].trim());
		Integer capacidadA = Integer.parseInt(e[2].trim());
		checkCapacidad(capacidadA);
		capacidad = capacidadA;
		TipoEspacio tipoA = null;
		switch(e[3].trim()) {
			case "EXAMEN":
				tipoA = TipoEspacio.EXAMEN;
				break;
			case "LABORATORIO":
				tipoA = TipoEspacio.LABORATORIO;
				break;
			case "OTRO":
				tipoA = TipoEspacio.OTRO;
				break;
			case "SEMINARIO":
				tipoA = TipoEspacio.SEMINARIO;
				break;
			case "TEORIA":
				tipoA = TipoEspacio.TEORIA;
				break;
		}
		tipo = tipoA;
	}

	public String getNombre() {
		return nombre;
	}

	public TipoEspacio getTipo() {
		return tipo;
	}

	public Integer getCapacidad() {
		return capacidad;
	}

	public Integer getPlanta() {
		return planta;
	}
	
	private void checkCapacidad(Integer capacidad2) {
		if(capacidad2 <= 0){
			throw new ExcepcionEspacioNoValido("La capacidad debe de ser superior a 1.");
		}
	}

	public void setTipo(TipoEspacio tipo) {
		this.tipo = tipo;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setCapacidad(Integer capacidad) {
		checkCapacidad(capacidad);
		this.capacidad = capacidad;
	}

	public String toString() {
		return getNombre() + " (planta " + getPlanta() + ") ";
	}

	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null)
			return false;
		boolean res = false;
		if (o instanceof Espacio) {
			Espacio e = (Espacio) o;
			res = this.getNombre().equals(e.getNombre())
					&& this.getPlanta().equals(e.getPlanta());
		}
		return res;
	}

	public int hashCode() {
		return getNombre().hashCode() * 31 + getPlanta().hashCode() * 31;
	}

	public int compareTo(Espacio e) {
		int res;
		if (e == null) {
			throw new NullPointerException();
		}
		res = getPlanta().compareTo(e.getPlanta());
		if (res == 0) {
			res = getNombre().compareTo(e.getNombre());
		}
		return res;
	}

}
