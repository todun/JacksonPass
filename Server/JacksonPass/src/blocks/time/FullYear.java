package blocks.time;

import javax.servlet.http.HttpServletRequest;
import blocks.IBlock;

public class FullYear extends Time implements IBlock {

	@Override
	public String getShortName() {
		return "Full Year";
	}

	@Override
	public String getFullName() {
		return "Full Year";
	}

	@Override
	public String getPattern() {
		return "%FullYear%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return format("yyyy");
	}
}