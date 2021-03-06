package blocks.time;

import javax.servlet.http.HttpServletRequest;

import com.sleepycat.persist.model.Persistent;

import blocks.IBlock;

@Persistent
public class FullMonth extends Time implements IBlock {

	@Override
	public String getShortName() {
		return "Full Month";
	}

	@Override
	public String getFullName() {
		return "Full name of Month";
	}

	@Override
	public String getPattern() {
		return "%FullMonth%";
	}

	@Override
	public String getRepresentation(HttpServletRequest req) {
		return format("MMMM");
	}
}
