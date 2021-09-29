package it.eserciziofo.utility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.testng.asserts.IAssert;
import org.testng.asserts.SoftAssert;
import org.testng.collections.Maps;

public class checkAssert extends SoftAssert {

//	ExtentTest reportLinksEsterno = .report.createNode("Controllo attributo href esterno");
	private Map<IAssert<?>, AssertionError> m_errors = Maps.newLinkedHashMap();
	private List<String> success = new ArrayList<String>();

	public Map<IAssert<?>, AssertionError> getM_errors() {
		return m_errors;
	}

	/**
	 * 
	 * @return list di string
	 */
	public List<String> getSuccess() {
		return success;
	}

	@Override
	protected void doAssert(IAssert<?> a) {
		try {
			a.doAssert();
			success.add(a.getMessage());
		} catch (AssertionError ex) {
			onAssertFailure(a, ex);
			m_errors.put(a, ex);
		}
	}

	@Override
	public void assertAll() {
		// TODO Auto-generated method stub
		super.assertAll();
	}

	@Override
	public void assertAll(String message) {
		if (!m_errors.isEmpty()) {
			StringBuilder sb = new StringBuilder(null == message ? "elenco errori!" : message);
			boolean first = true;
			for (IAssert<?> error : m_errors.keySet()) {
				if (first) {
					first = false;
				} else {
					sb.append(",");
				}
				sb.append("\n");
				sb.append(error.getMessage());
			}
			throw new AssertionError(sb.toString());
		}
	}

}
