package auction.gui;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.log4j.Logger;

import auction.gui.broadcaster.BroadcastType;
import auction.gui.broadcaster.Broadcaster;
import auction.gui.broadcaster.IBroadcastListener;
import auction.service.client.AuctionSEI;
import auction.service.client.AuctionService;
import auction.service.client.BidFromService;
import auction.service.client.LotFromService;
import auction.service.client.UserFrowService;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.data.util.converter.Converter.ConversionException;
import com.vaadin.data.validator.StringLengthValidator;
import com.vaadin.event.ItemClickEvent;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinService;
import com.vaadin.shared.ui.datefield.Resolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Component;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.GridLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.PopupDateField;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.BaseTheme;
import com.vaadin.ui.themes.Reindeer;

@SuppressWarnings("serial")
@Theme("auction")
@Push
public class AuctionUI extends UI implements IBroadcastListener {
	
	private static final Logger log = Logger.getLogger(AuctionUI.class);
	private VerticalLayout mainLayout;
	private Table lotsTable;
	private Table bidsTable;
	private AuctionSEI port;

	private String userId = "";
	private String userName = "";
	private GridLayout grLayout;
	private Button btnCancelTrades;
	private Button btnNewBid;
	private Label lblUser;

	@Override
	public void receiveBroadcast(final BroadcastType type, final Object smthNew) {
        access(new Runnable() {
            @Override
            public void run() {
            	switch(type){
            		case NEW_LOT:
        				lotsTable.addItem(new Object[]{
        						((LotFromService) smthNew).getCode(), 
        						((LotFromService) smthNew).getName(), 
        						((LotFromService) smthNew).getFinish(), 
        						((LotFromService) smthNew).getState()}, 
        						lotsTable.size() + 1);
						break;
					case NEW_BID:
						Object rowId = lotsTable.getValue(); 
						int selectedLotId = Integer.parseInt((String) lotsTable
								.getContainerProperty(rowId,"Code").getValue());
						
						if (selectedLotId == Integer.parseInt(((BidFromService) smthNew)
							.getLot())) {
							bidsTable.addItem(new Object[]{
	        						((BidFromService) smthNew).getBid(), 
	        						((BidFromService) smthNew).getDate(), 
	        						((BidFromService) smthNew).getBidder()}, 
	        						bidsTable.size() + 1);
						}
						break;
					case CHANGED_LOT_STATE:
						//TODO
						break;
					default:
						break;
            	}
            }
        });
	}
	
	@Override
	protected void init(VaadinRequest request) {
		
		AuctionService service = new AuctionService();
		port = service.getAuctionPort();
		
		authentication();
		start();
		Broadcaster.register(this);
	}

	@Override
	public void detach() {
		Broadcaster.unregister(this);
		super.detach();
	}
	
	private void authentication() {
		Cookie[] cookies = VaadinService.getCurrentRequest().getCookies();
		for (Cookie cookie : cookies) { 
			if ("userId".equals(cookie.getName())) { 
				userId = cookie.getValue(); 
				log.trace("userId & userName: " + userId + " " + userName + "max age: " + cookie.getMaxAge());
			} else if ("userName".equals(cookie.getName())) { 
				userName = cookie.getValue(); 
			} 
		}
		
		if (userId.length() != 0) { //user refreshed the page
			start();
			return;
		}

		VerticalLayout authLayout = new VerticalLayout();
		authLayout.setWidth("340px");
		
		FormLayout centralLayout = new FormLayout();
		centralLayout.setSizeFull();
		authLayout.addComponent(centralLayout);
		
		HorizontalLayout footerLayout = new HorizontalLayout();
		footerLayout.setSizeFull();
		authLayout.addComponent(footerLayout);

		final TextField loginField = new TextField("Login");
		loginField.setHeight("25pt");
		loginField.addValidator(new StringLengthValidator(
		    "The login must be 1-20 letters", 1, 20, true));
		loginField.setImmediate(true);
		centralLayout.addComponent(loginField);

		final PasswordField passField = new PasswordField("Password");
		passField.setHeight("25pt");
		passField.addValidator(new StringLengthValidator(
		    "The password must be 1-20 letters", 1, 20, true));
		passField.setImmediate(true);
		centralLayout.addComponent(passField);

        Button btnLogin = new Button("Login");
        Button btnReg = new Button("Register");
        btnLogin.addStyleName("btnLogin");
        btnReg.addStyleName(Reindeer.BUTTON_LINK);
        btnReg.addStyleName("reglink");

        footerLayout.addComponent(btnLogin);
        footerLayout.addComponent(btnReg);
		
		final Window authWindow = new Window("Authentication");
		authWindow.setContent(authLayout);
		authWindow.center();
		authWindow.setModal(true);
        addWindow(authWindow);

        btnLogin.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				log.trace(loginField.getValue() + "; length=" + loginField.getValue().length());
				log.trace(passField.getValue() + "; length=" + passField.getValue().length());
				

		    	loginField.setValidationVisible(false);
		    	passField.setValidationVisible(false);
		        try {
		        	loginField.validate();
		        	passField.validate();
		        } catch (InvalidValueException e) {
		            Notification.show(e.getMessage());
		            
		            loginField.setValidationVisible(true);
			    	passField.setValidationVisible(true);
			    	return;
		        }
				
		        UserFrowService auth = new UserFrowService();
				auth.setLogin(loginField.getValue());
				auth.setPassword(passField.getValue());
				
				UserFrowService response = port.authentication(auth);
				log.trace(response);
				
				if (response != null) {
					//TODO Problem with cookies!
					
					userId = response.getId();
					userName = response.getFname() + " " + response.getLname();
					log.trace("userId; userName:" + userId + "; " + userName); 
					
//					Cookie userIdCookie = new Cookie("userId", response.getId());
//					Cookie userNameCookie = new Cookie("userName", response.getFname() + " " + response.getLname());
//					
//					log.trace("getCurrentRequest: " + VaadinService.getCurrentRequest());
//					log.trace("getCurrentResponse: " + VaadinService.getCurrentResponse());
					
					String path = VaadinService.getCurrentRequest().getContextPath();
					log.trace("path=" + path); 
//					userIdCookie.setPath(path);
//					userNameCookie.setPath(path);
//				    VaadinService.getCurrentResponse().addCookie(userIdCookie);
//				    VaadinService.getCurrentResponse().addCookie(userNameCookie);
				    
					authWindow.close();
					start();
				}
				else {
		            Notification.show("Incorrect login or password!");
				}
			}
        });
        
        btnReg.addClickListener(new Button.ClickListener(){
			public void buttonClick(ClickEvent event) {
				VerticalLayout regLayout = new VerticalLayout();
				regLayout.setWidth("350px");
				
				FormLayout centralLayout = new FormLayout();
				centralLayout.setSizeFull();
				regLayout.addComponent(centralLayout);
				
				HorizontalLayout footerLayout = new HorizontalLayout();
				footerLayout.setSizeFull();
				regLayout.addComponent(footerLayout);
				
				final TextField loginField = new TextField("Login");
				loginField.setHeight("25pt");
				loginField.setRequired(true);
				loginField.addValidator(new StringLengthValidator(
				    "The login must be 1-20 letters", 1, 20, true));
				loginField.setRequiredError("The login must not be empty");
				loginField.setImmediate(true);
				centralLayout.addComponent(loginField);
				
				final PasswordField passField = new PasswordField("Password");
				passField.setHeight("25pt");
				passField.setRequired(true);
				passField.addValidator(new StringLengthValidator(
				    "The password must be 1-20 letters", 1, 20, true));
				passField.setRequiredError("The password must not be empty");
				passField.setImmediate(true);
				centralLayout.addComponent(passField);
				
				final TextField fNameField = new TextField("First name");
				fNameField.setHeight("25pt");
				fNameField.setRequired(true);
				fNameField.addValidator(new StringLengthValidator(
				    "First name must be 1-15 letters", 1, 15, true));
				fNameField.setRequiredError("First name must not be empty");
				fNameField.setImmediate(true);
				centralLayout.addComponent(fNameField);
				
				final TextField lNameField = new TextField("Last name");
				lNameField.setHeight("25pt");
				lNameField.setRequired(true);
				lNameField.addValidator(new StringLengthValidator(
				    "Last name must be 1-15 letters", 1, 15, true));
				lNameField.setRequiredError("Last name must not be empty");
				lNameField.setImmediate(true);
				centralLayout.addComponent(lNameField);

				final Window regWindow = new Window("Registration");
				
				Button btnReg = new Button("Register");
				btnReg.addClickListener(new Button.ClickListener(){
				    public void buttonClick(ClickEvent event) {
				    	loginField.setValidationVisible(false);
				    	passField.setValidationVisible(false);
				    	fNameField.setValidationVisible(false);
				    	lNameField.setValidationVisible(false);
				        try {
				        	loginField.validate();
				        	passField.validate();
				        	fNameField.validate();
				        	lNameField.validate();
				        	
				        	UserFrowService user = new UserFrowService();
				        	user.setLogin(loginField.getValue());
				        	user.setPassword(passField.getValue());
				        	user.setFname(fNameField.getValue());
				        	user.setLname(lNameField.getValue());

				        	if (port.registration(user) > 0) {
				        		regWindow.close();
						    	authWindow.setVisible(true);
				        	} else {
				        		//TODO current login already exist
				        	}
				        } catch (InvalidValueException e) {
				            Notification.show(e.getMessage());
				            
				            loginField.setValidationVisible(true);
					    	passField.setValidationVisible(true);
					    	fNameField.setValidationVisible(true);
					    	lNameField.setValidationVisible(true);
				        }
				    }
				});
				Button btnCancel = new Button("Cancel");
				btnCancel.addClickListener(new Button.ClickListener(){
				    public void buttonClick(ClickEvent event) {
				    	regWindow.close();
				    	authWindow.setVisible(true);
				    }
				});

				footerLayout.addComponent(btnReg);
				footerLayout.addComponent(btnCancel);
				footerLayout.setComponentAlignment(btnReg, Alignment.MIDDLE_CENTER);
				footerLayout.setComponentAlignment(btnCancel, Alignment.MIDDLE_CENTER);

				regWindow.setContent(regLayout);
				regWindow.center();
				regWindow.setModal(true);
				
				authWindow.setVisible(false);
		        addWindow(regWindow);
			}
        	
        });
	}
	
	private void start(){
		initLayouts();
		getAllLots();
	}

	private void initLayouts() {
		mainLayout = new VerticalLayout();
		mainLayout.setMargin(true);
//		mainLayout.setSpacing(true);
		setContent(mainLayout);

		Label lblAuction = new Label("Auction");
		lblAuction.setSizeUndefined();
		lblAuction.addStyleName("lblAuction");
		
		lblUser = new Label("User: " + userName);
		lblUser.setSizeUndefined();
		lblUser.addStyleName("lblUser");
		
		Button btnLogout = new Button("Logout");
		btnLogout.setSizeUndefined();
		btnLogout.setStyleName(BaseTheme.BUTTON_LINK);
		btnLogout.addStyleName("btnLogout");		
		btnLogout.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
                Cookie cookie = new Cookie("userId", "");
                cookie.setPath(VaadinService.getCurrentRequest().getContextPath());
                cookie.setMaxAge(0);
                VaadinService.getCurrentResponse().addCookie(cookie);
                
                close();
			}
		});

		HorizontalLayout topHlayout = new HorizontalLayout();
		topHlayout.addComponent(lblAuction);
		topHlayout.addComponent(lblUser);
		topHlayout.addComponent(btnLogout);
		topHlayout.setExpandRatio(lblAuction, 1);
		topHlayout.setWidth("1550px");
		
		mainLayout.addComponent(topHlayout);

		HorizontalLayout middleHlayout = new HorizontalLayout();
		middleHlayout.setSpacing(true);

		Panel lotsPanel = new Panel("Lots"); 
		initLotsPanel(lotsPanel);//***********

		VerticalLayout lotDetailsVlayout = new VerticalLayout();
		lotDetailsVlayout.setSpacing(true);

		middleHlayout.addComponent(lotsPanel);
		middleHlayout.addComponent(lotDetailsVlayout);
		mainLayout.addComponent(middleHlayout);

		Panel lotDetailsPanel = new Panel("Lot details");
		initLotDetailsPanel(lotDetailsPanel);
		Panel bidsPanel = new Panel("Bids");
		initBidsPanel(bidsPanel);
		
		lotDetailsVlayout.addComponent(lotDetailsPanel);
		lotDetailsVlayout.addComponent(bidsPanel);		
	}

	private void initLotsPanel(Panel panel) {	
		panel.setSizeUndefined();
		
		FormLayout content = new FormLayout();		
		lotsTable = new Table();		
		final Button btnNewLot = new Button("New lot");
		
		content.addComponent(lotsTable);
		content.addComponent(btnNewLot);
		content.setSizeUndefined();
		content.setMargin(true);
		panel.setContent(content);
        
		lotsTable.addContainerProperty("Code", String.class, null);
		lotsTable.addContainerProperty("Name", String.class, null);
		lotsTable.addContainerProperty("Finish date",  String.class, null);
		lotsTable.addContainerProperty("State", String.class, null);
		
		lotsTable.setWidth("480px");		        
		lotsTable.setPageLength(23);
		lotsTable.setSelectable(true);        
		lotsTable.setImmediate(true);
		

		lotsTable.addItemClickListener(new ItemClickEvent.ItemClickListener() {
			public void itemClick(ItemClickEvent event) {
		    	onLotsTableClicked(Integer.parseInt(
		    			(String) lotsTable.getItem(event.getItemId())
		    			.getItemProperty("Code").getValue()));
			}
		});
		btnNewLot.addClickListener(new Button.ClickListener(){
			public void buttonClick(ClickEvent event) {
				VerticalLayout regLayout = new VerticalLayout();
				regLayout.setWidth("360px");
				
				FormLayout centralLayout = new FormLayout();
				centralLayout.setSizeFull();
				regLayout.addComponent(centralLayout);
				
				HorizontalLayout footerLayout = new HorizontalLayout();
				footerLayout.setSizeFull();
				regLayout.addComponent(footerLayout);
				
				final TextField lotNameField = new TextField("Lot name");
				lotNameField.setHeight("25pt");
				lotNameField.setWidth("220px");
				lotNameField.setRequired(true);
				lotNameField.setMaxLength(25);
				lotNameField.setRequiredError("The lot name must not be empty");
				lotNameField.setImmediate(true);
				centralLayout.addComponent(lotNameField);
				
				final PopupDateField finishDateField = new PopupDateField("Finish date");
				finishDateField.setValue(new Date());
				finishDateField.setResolution(Resolution.SECOND);
				finishDateField.setDateFormat("dd.MM.yyyy HH:mm:ss");
				finishDateField.setWidth("220px");
				finishDateField.setRequired(true);
				finishDateField.setStyleName("date");
				centralLayout.addComponent(finishDateField);
				
				final TextField priceField = new TextField("Start price");
				priceField.setConverter(Float.class);
				priceField.setValue("0");
				priceField.setHeight("25pt");
				priceField.setWidth("220px");
				priceField.setRequired(true);
				centralLayout.addComponent(priceField);
				
				final TextArea descriptionArea = new TextArea("Description");
				descriptionArea.setHeight("50pt");
				descriptionArea.setWidth("220px");
				descriptionArea.setMaxLength(50);
				centralLayout.addComponent(descriptionArea);

				final Window newLotWindow = new Window("New lot");
				
				Button btnCreate = new Button("Create");
				btnCreate.addClickListener(new Button.ClickListener(){
				    public void buttonClick(ClickEvent event) {
				    	log.trace(priceField.getValue());
				    	
				    	lotNameField.setValidationVisible(false);
				        try {
				        	lotNameField.validate();
				        } catch (InvalidValueException e) {
				            Notification.show(e.getMessage());
				            lotNameField.setValidationVisible(true);
				            return;
				        }
				        
			        	float convertedValue;
			        	if (priceField.getValue() == null){
			                Notification.show("The price must not be empty.");
			                return;
			        	}
			        	try {
			        		convertedValue = (Float) priceField.getConvertedValue();
			        		if (convertedValue < 0) {
				                Notification.show("The price must be positive.");
				                return;
			        		}
			            } catch (ConversionException e) {
			                Notification.show("Could not convert price: must be a float value.");
			                return;
			            }
			        	
			        	/*
			        	 * "����� ������" ������ ���� �������� �������. ��� ��������� �������,
			        	 * ���������� �������������, ����� ������������ "������� �����" + 2 �������,
			        	 * ��������� ���������� �������� ������ ��������� �����.
			        	 */
			        	Calendar curTimePlusTwoSec = Calendar.getInstance();
			        	curTimePlusTwoSec.add(Calendar.SECOND, 2);
			        	if (finishDateField.getValue().compareTo(curTimePlusTwoSec.getTime()) == -1){
			                Notification.show("Finish date must be future.");
			                return;
			        	}
			        		
			        	LotFromService lot = new LotFromService();
			        	lot.setOwner(userId);
			        	lot.setName(lotNameField.getValue());
			        	lot.setFinish(new SimpleDateFormat("dd.MM.yyyy HH:mm:ss")
			        		.format(finishDateField.getValue().getTime()));
			        	lot.setPrice(String.valueOf(convertedValue).replaceAll(",", ".").replaceAll(" ", ""));
			        	lot.setDescription(descriptionArea.getValue());

			        	LotFromService newlot = port.newLot(lot);
			        	if (newlot == null) {
			        		
			        	} else {
			        		//TODO �������� �������� �� ���������� ������ � �� ��� �����������
			        	}
			        	
			        	Broadcaster.broadcast(BroadcastType.NEW_LOT, newlot);
			        	log.trace("broadcasted new lot");
		        		newLotWindow.close();
				    }
				});
				Button btnCancel = new Button("Cancel");
				btnCancel.addClickListener(new Button.ClickListener(){
				    public void buttonClick(ClickEvent event) {
				    	newLotWindow.close();
				    }
				});

				footerLayout.addComponent(btnCreate);
				footerLayout.addComponent(btnCancel);
				footerLayout.setComponentAlignment(btnCreate, Alignment.MIDDLE_CENTER);
				footerLayout.setComponentAlignment(btnCancel, Alignment.MIDDLE_CENTER);

				newLotWindow.setContent(regLayout);
				newLotWindow.center();
				newLotWindow.setModal(true);
		        addWindow(newLotWindow);
			}
		});
	}

	private void initLotDetailsPanel(Panel panel) {
		grLayout = new GridLayout(3, 8);
		grLayout.setSpacing(true);
		grLayout.setSizeFull();
		grLayout.setWidth("1000px");
		grLayout.setColumnExpandRatio(1, 1);

		grLayout.addComponent(new Label("Code:"), 0, 0);
		grLayout.addComponent(new Label("Name:"), 0, 1);
		grLayout.addComponent(new Label("State:"), 0, 2);
		grLayout.addComponent(new Label("Finish date:"), 0, 3);
		grLayout.addComponent(new Label("Owner:"), 0, 4);
		grLayout.addComponent(new Label("Remaining time:"), 0, 5);
		grLayout.addComponent(new Label("Description:"), 0, 6);
		grLayout.addComponent(new Label("Start price:"), 0, 7);	
	    for (int row=0; row<8; row++) {
	        Component c = grLayout.getComponent(0, row);
        	c.setWidth(null);
        	grLayout.setComponentAlignment(c, Alignment.MIDDLE_RIGHT);
	    }

		grLayout.addComponent(new Label(""), 1, 0);
		grLayout.addComponent(new Label(""), 1, 1);
		grLayout.addComponent(new Label(""), 1, 2);
		grLayout.addComponent(new Label(""), 1, 3);
		grLayout.addComponent(new Label(""), 1, 4);
		grLayout.addComponent(new Label(""), 1, 5);
		grLayout.addComponent(new Label(""), 1, 6);
		grLayout.addComponent(new Label(""), 1, 7);	

		btnCancelTrades = new Button("Cancel trades");
		grLayout.addComponent(btnCancelTrades, 2, 0, 2, 7);	
		grLayout.setComponentAlignment(btnCancelTrades, Alignment.BOTTOM_RIGHT);
		btnCancelTrades.setVisible(false);
		btnCancelTrades.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				int cancelledLotId = Integer.parseInt(((Label)grLayout.getComponent(1, 0))
						.getValue()); 
				log.trace("cancelling lot with id=" + cancelledLotId);
				LotFromService cancelledLot = port.cencelTheLot(cancelledLotId);
				Broadcaster.broadcast(BroadcastType.CHANGED_LOT_STATE, cancelledLot);
			}
		});
		
		panel.setContent(grLayout);
	}
	
	private void initBidsPanel(Panel panel) {		
		panel.setSizeUndefined();
		VerticalLayout content = new VerticalLayout();
		bidsTable = new Table();
		btnNewBid = new Button("New bid");

		content.addComponent(bidsTable);
		content.setExpandRatio(bidsTable, 1);
		content.addComponent(btnNewBid);
		btnNewBid.setVisible(false);
		
		content.setSizeUndefined(); // Shrink to fit
		content.setMargin(true);
		content.setWidth("1000px");
		content.setHeight("361px");
		panel.setContent(content);
        
		// Define columns for the built-in container
		bidsTable.addContainerProperty("Bid", String.class, null);
		bidsTable.addContainerProperty("Date", String.class, null);
		bidsTable.addContainerProperty("Bidder",  String.class, null);
				        
		bidsTable.setPageLength(11);
		bidsTable.setWidth("100%");
		bidsTable.setHeight("294px");
		bidsTable.setSelectable(true);        
		bidsTable.setImmediate(true);
		
		btnNewBid.addClickListener(new Button.ClickListener(){
			public void buttonClick(ClickEvent event) {				
				VerticalLayout newBidLayout = new VerticalLayout();
				newBidLayout.setWidth("280px");
				
				FormLayout centralLayout = new FormLayout();
				centralLayout.setSizeFull();
				newBidLayout.addComponent(centralLayout);
				
				HorizontalLayout footerLayout = new HorizontalLayout();
				footerLayout.setSizeFull();
				newBidLayout.addComponent(footerLayout);

				/* 
				 * currentMaxBid - ������� ������������ ������ �� ���.
				 * ���� ������ ��� �� ����, �� currentMaxBid = ��������� ���� ����
				 */
				final float currentMaxBid = (bidsTable.size() != 0) ?
					(float) bidsTable.getItem(bidsTable.size()).getItemProperty("Bid").getValue() : 
						Float.valueOf(((Label)grLayout.getComponent(1, 7)).getValue());
				log.trace(currentMaxBid);	

				final TextField bidField = new TextField("Bid");
				bidField.setConverter(Float.class);
				bidField.setValue(String.valueOf(currentMaxBid + 1).replace(".", ","));
//				bidField.setMaxLength(50);
				bidField.setHeight("25pt");
//				bidField.setWidth("220px");
				bidField.setRequired(true);
				centralLayout.addComponent(bidField);	

				final Window newBidWindow = new Window("New bid");
				
				Button btnOk = new Button("Ok");
				btnOk.addClickListener(new Button.ClickListener(){
				    public void buttonClick(ClickEvent event) {
				    	log.trace(bidField.getValue());
				        
			        	float convertedValue;
			        	if (bidField.getValue() == null){
			                Notification.show("The bid must not be empty.");
			                return;
			        	}
			        	try {
			        		convertedValue = (Float) bidField.getConvertedValue();
			        		if (convertedValue <= currentMaxBid ) {
				                Notification.show("Your bid must be greater than last bid.");
				                return;
			        		}
			            } catch (ConversionException e) {
			                Notification.show("Could not convert bid: must be a float value.");
			                return;
			            }

						
			        	BidFromService bid = new BidFromService();
			        	bid.setBidder(userId);
			        	bid.setBid(String.valueOf(convertedValue).replaceAll(",", ".").replaceAll(" ", ""));
			        	bid.setLot((String) lotsTable.getItem(lotsTable.getValue())
			        		.getItemProperty("Code").getValue());

			        	BidFromService newBid = port.newBid(bid);
			        	if (newBid == null) {
			        		
			        	} else {
			        		//TODO �������� �������� �� ���������� ������ � �� ��� �����������
			        	}
			        	
			        	Broadcaster.broadcast(BroadcastType.NEW_BID, newBid);
			        	newBidWindow.close();
				    }
				});

				footerLayout.addComponent(btnOk);
				footerLayout.setComponentAlignment(btnOk, Alignment.MIDDLE_CENTER);

				newBidWindow.setContent(newBidLayout);
				newBidWindow.center();
				newBidWindow.setModal(true);
		        addWindow(newBidWindow);
			}
		});
	}

	private void getAllLots() {      		
		List<LotFromService> response = port.getLots();
		lotsTable.removeAllItems();
		
		int n = 0; //����� ������
		for (LotFromService el : response) {
			lotsTable.addItem(new Object[]{el.getCode(), el.getName(), el.getFinish(), el.getState()}, n++);
		}
	}

	private void onLotsTableClicked(int value) {    
		log.trace("getting lot with id=" + value);
		
		LotFromService response = port.getLotInfo(value);

		((Label) grLayout.getComponent(1, 0)).setValue(response.getCode());
		((Label) grLayout.getComponent(1, 1)).setValue(response.getName());
		((Label) grLayout.getComponent(1, 2)).setValue(response.getState());
		((Label) grLayout.getComponent(1, 3)).setValue(response.getFinish());
		((Label) grLayout.getComponent(1, 4)).setValue(response.getOwner());
		
		((Label) grLayout.getComponent(1, 6)).setValue(response.getDescription());
		((Label) grLayout.getComponent(1, 7)).setValue(response.getPrice());
		
		//���� ��� � Id=value ������������ ������������, �� �� ����� ���������� ����� �� ����
		log.trace("selected lot is " + ((Label)grLayout.getComponent(1, 2)).getValue());
		btnCancelTrades.setVisible((response.getOwner().equals(userName) &&
				((Label)grLayout.getComponent(1, 2)).getValue().equals("ACTIVE")) ? true : false);
		//���� ��� � Id=value ��� �������, �� �� ���� ����� ������� ������.
		//������������ �� ����� ������� ������ �� ���� ���
		btnNewBid.setVisible((((Label)grLayout.getComponent(1, 2)).getValue().equals("ACTIVE") &&
			!((Label)grLayout.getComponent(1, 4)).getValue().equals(userName)) ? true : false);
		
		getBidsOnLot(value);
	}
	
	private void getBidsOnLot(int id) {
		List<BidFromService> response = port.getBidsOnLot(id);

		if (response != null) {
			bidsTable.removeAllItems();		
			int n = 1; //����� ������
			for (BidFromService el : response) {
				bidsTable.addItem(new Object[]{el.getBid(), el.getDate(), el.getBidder()}, n++);
			}
		}
	}

}