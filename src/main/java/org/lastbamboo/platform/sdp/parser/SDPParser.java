package org.lastbamboo.platform.sdp.parser;


import java.text.ParseException;

import org.lastbamboo.platform.sdp.ParserCore;
import org.lastbamboo.platform.sdp.fields.SDPField;

/**
*@version  JAIN-SIP-1.1
*
*@author M. Ranganathan <mranga@nist.gov>  <br/>
*
*<a href="{@docRoot}/uncopyright.html">This code is in the public domain.</a>
*
*/
public abstract class SDPParser extends ParserCore {

	public abstract SDPField parse() throws ParseException;

}