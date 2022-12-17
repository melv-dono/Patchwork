package Model;

/**
 * <b>Case represents the type of tile you can find the timeboard</b>
 * <p>
 * You can find 3 types of Cases :
 * <ul>
 * <li>NEUTRAL : refers to a case with no effect</li>
 * <li>BUTTON : refers to a case with buttons income</li>
 * <li>SPECIAL_PATCH : refers to a case where you can earn a patch with a dimension 1 by 1</li>
 * </ul>
 * </p>
 * <p>
 * @author Mickaël RAKOTOARISON, Melvyn NZENGA.
 */
public enum Case {
	NEUTRAL, SPECICAL_PATCH, BUTTON;
}
