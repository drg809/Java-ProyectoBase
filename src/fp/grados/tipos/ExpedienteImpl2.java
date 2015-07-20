package fp.grados.tipos;

import java.util.OptionalDouble;

public class ExpedienteImpl2 extends ExpedienteImpl {
	public Double getNotaMedia() {
		Double notaMedia = 0.0;
		OptionalDouble res = getNotas().stream().filter(n -> n.getValor() >= 5).mapToDouble(n -> n.getValor()).average();
		
		if(res.isPresent()) {
			notaMedia = res.getAsDouble();
		} 
		return notaMedia;
	}
}
