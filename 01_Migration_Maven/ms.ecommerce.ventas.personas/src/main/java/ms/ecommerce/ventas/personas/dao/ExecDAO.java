package ms.ecommerce.ventas.personas.dao;

import java.sql.Types;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import lombok.extern.slf4j.Slf4j;
import ms.ecommerce.ventas.personas.helpers.Helper;
import ms.ecommerce.ventas.personas.models.Response;

@Repository
@Slf4j
public class ExecDAO implements IExecDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private SimpleJdbcCall query;

	@Override
	public Response execStoredProcedure(Object object, String name) {
		MapSqlParameterSource sqlParams = new MapSqlParameterSource();
		Response response = new Response();
		var resultText = "";
		try {
			query = new SimpleJdbcCall(jdbcTemplate.getDataSource()).withProcedureName(name).declareParameters(
							new SqlParameter("@xmlText", Types.NVARCHAR), 
							new SqlOutParameter("@ResultText", Types.NVARCHAR)
							);
			sqlParams.addValue("@xmlText", Helper.objectToXml(object, object.getClass()));
			Map<String, Object> map = query.execute(sqlParams);
			resultText = (String) map.get("@ResultText");

		} catch (Exception e) {
			response.setIsBreakOperation("true");
			response.setIsCorrect("false");
			response.setMessage(e.getMessage());
			log.error("Error al ejecutar SP:  " + e.toString());
			return response;
		}
		try {
			response = (Response) Helper.xmlToObject(resultText, response);
		} catch (Exception i) {
			response.setIsBreakOperation("true");
			response.setIsCorrect("false");
			response.setMessage(i.getMessage());
			log.error("Error al deserializar objeto XML:  " + i.toString());
		}
		return response;
	}
}
