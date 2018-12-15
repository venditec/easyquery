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

import java.util.LinkedList;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;

import easyquery.client.etc.icon.Icon;

/**
 * This is a element of the tree.
 * An element contains a name, an icon and a list of child elements.
 * @author Ramon Segarra Pijuan & Marceli Alet Alis
 */
public class TreeElement extends HorizontalPanel {
	
	private Label Name;
	private Image Icon;
	private LinkedList<TreeElement> sonElements;
	private boolean Open;
	private HorizontalPanel HPSpace;
	
	public TreeElement(){
		HPSpace = new HorizontalPanel();
		add(HPSpace);
		setStyleName("treeElement");
		setOpen(false);
		Icon = new Image();
		setIcon(getIconTerminal());
		getIcon().setStyleName("Icon");
		getIcon().addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
			    if(isOpen()) setOpen(false);
			    else setOpen(true);
				for(int i=0;i<getSonElements().size();i++){
					if(isOpen()){
						setIcon(getIconOpen());
						Open(getSonElements().get(i));
					}else{
						setIcon(getIconClose());
						Close(getSonElements().get(i));
					}
				}
			}
			
		});
		add(Icon);
	}
	
	/**
	 * @param name, Element name
	 */
	public void setName(Label name) {
		Name = name;
		Name.setStyleName("treeElementName");
		add(Name);
		setCellHorizontalAlignment(Name, ALIGN_LEFT);
		Name.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				if(isOpen()) setOpen(false);
			    else setOpen(true);
				for(int i=0;i<getSonElements().size();i++){
					if(isOpen()){
						setIcon(getIconOpen());
						Open(getSonElements().get(i));
					}else{
						setIcon(getIconClose());
						Close(getSonElements().get(i));
					}
				}
			}
		});
	}
	
	/**
	 * @return Element name
	 */
	public Label getName() {
		return Name;
	}
	
	/**
	 * @param sonElements, Add son elements
	 */
	public void setSonElements(LinkedList<TreeElement> sonElements) {
		this.sonElements = sonElements;
		if(sonElements.size()>0)
			setIcon(getIconClose());
	}
	
	/**
	 * @return obtain the son elements
	 */
	public LinkedList<TreeElement> getSonElements() {
		return sonElements;
	}
	
	/**
	 * @param open, if this element is open or is close
	 * true => open, false => close
	 */
	public void setOpen(boolean open) {
		Open = open;
	}
	
	/**
	 * @return	true  => is open
	 * 			false => is close
	 */
	public boolean isOpen() {
		return Open;
	}
	
	public void setHPSpace(HTML hPSpace) {
		HPSpace.add(hPSpace);
	}
	
	public HorizontalPanel getHPSpace() {
		return HPSpace;
	}
	
	/**
	 * @param tree, open this treeElement
	 */
	public void Open(TreeElement tree){
		tree.setVisible(true);
	}
	
	/**
	 * @param tree, close this treeElement
	 */
	public void Close(TreeElement tree){
		tree.setVisible(false);
		tree.setOpen(false);
		if(tree.getSonElements().size()>0)
			tree.setIcon(getIconClose());
		for(int i=0; i<tree.getSonElements().size(); i++)
			Close(tree.getSonElements().get(i));
	}
	
	/**
	 * @param icon, the icon of this element
	 */
	public void setIcon(Image icon) {
		Icon.setUrl(icon.getUrl());
	}
	
	/**
	 * @return the icon
	 */
	public Image getIcon() {
		return Icon;
	}

	public Image getIconOpen() {
		Image img = new Image();
		img.setUrl(new Icon().TreeOpen);
		return img;
	}

	public Image getIconClose() {
		Image img = new Image();
		img.setUrl(new Icon().TreeClose);
		return img;
	}

	public Image getIconTerminal() {
		Image img = new Image();
		img.setUrl(new Icon().TreeTerminal);
		return img;
	}
}
