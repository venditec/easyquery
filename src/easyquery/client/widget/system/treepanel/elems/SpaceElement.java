/*
TreePanel is a part of InnovaCampus Web Application source code
http://sedna.udl.cat/projects/InnovaCampus

Copyright (C) 2010 Ramon Segarra Pijuan & Marceli Alet Alis

TreePanel is a free software code: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

TreePanel code is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
GNU General Public License for more details.
*/

package easyquery.client.widget.system.treepanel.elems;

import com.google.gwt.user.client.ui.HTML;

/**
 * Horizontal space of the tree
 * @author Ramon Segarra Pijuan & Marceli Alet Alis
 */
public class SpaceElement extends HTML {

	/**
	 * @param numSpaces, Number of horitzontal spaces. One space is equivalent to one simple letter.
	 */
	public SpaceElement(int numSpaces){
		String codeHTML = "";
		for(int i=0;i<numSpaces;i++){
			codeHTML = codeHTML + "&nbsp;";
		}
		setHTML(codeHTML);
	}
}
