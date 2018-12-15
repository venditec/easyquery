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

package easyquery.client.widget.system.treepanel;

import com.google.gwt.user.client.ui.VerticalPanel;

import easyquery.client.widget.system.treepanel.elems.SpaceElement;

public class TreePanel extends VerticalPanel {
	
	/**
	 * This is the main tree panel
	 * @author Ramon Segarra Pijuan & Marceli Alet Alis
	 */
	
	public TreePanel(){
	}
	
	/**
	 * Show tree of the elements
	 * @param tree
	 * @param level
	 */
	public void showTreePanel(final TreeElement tree, int level){
		if(level != 0) tree.setVisible(false);
		else tree.setVisible(true);
		tree.setHPSpace(new SpaceElement(level));
		add(tree);
		level = level + 5;
		for(int i=0;i<tree.getSonElements().size();i++){
			showTreePanel(tree.getSonElements().get(i),level);
		}
	}
}
