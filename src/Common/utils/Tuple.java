/**
 * Tuple
 *
 * Version 1.0.0
 *
 * Created on 21/05/2014
 *
 * The following text is protected by GPLv2 licence
 * (http://www.gnu.org/licenses/gpl-2.0.html)
 */
package common.utils;

public class Tuple <T1, T2> {
	public final T1 fst;
	public final T2 snd;

	public Tuple(T1 fst, T2 snd) {
		this.fst = fst;
		this.snd = snd;
	}
}
