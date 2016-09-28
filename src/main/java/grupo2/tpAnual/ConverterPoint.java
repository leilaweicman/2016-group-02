package grupo2.tpAnual;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import org.uqbar.geodds.Point;

@Converter
public class ConverterPoint implements AttributeConverter<Point, String>{

    @Override
    public String convertToDatabaseColumn(Point point) {
        StringBuilder sb = new StringBuilder();
        sb.append(point.latitude()).append(",").append(point.longitude());
        return sb.toString();
    }

    @Override
    public Point convertToEntityAttribute(String pointString) {
        String[] ubicacion = pointString.split(",");
        return new Point(Integer.parseInt(ubicacion[0]),Integer.parseInt(ubicacion[1]));
    }

}