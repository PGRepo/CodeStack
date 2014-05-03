package com.coders.service;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.coders.databaseConnection.Database;
import com.coders.model.Boards;
import com.coders.model.Tacks;
import com.coders.model.UserMessage;
import com.coders.model.Users;

public class Services 
{
	
	Connection conn;
	UserMessage userMessage = new UserMessage();
	Users user = new Users();
	Statement stmt;
	public UserMessage signIn(String email,String password,String firstName,String lastName)
	{
		Database d=new Database();
		conn=d.connectToDatabase();
		//ArrayList<String> listOfDefaultImages;
		  if (conn != null) 
		  {
			  try {
              PreparedStatement prepstmt=conn.prepareStatement("select idUser,password from user where iduser=? and password=?");
              prepstmt.setString(1,email);
              prepstmt.setString(2,password);
              ResultSet rs = prepstmt.executeQuery();
              System.out.println("Query"+prepstmt);
              user = setUserBeanValues(rs,user);
              //listOfDefaultImages = displayDefaultImage();
              if(user != null){
					userMessage.setUser(user);
					//userMessage.setListOfDefaultImages(listOfDefaultImages);
					userMessage.setMessage("Welcome "+user.getFirstName()+" "+user.getLastName());		
					//displayDefaultImage();
				}else{
					userMessage.setMessage("Invalid Login Please try again!!!");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				userMessage.setMessage("Invalid Login Please try again!!!");
				e.printStackTrace();
			}
		  }
		  d.closeConnection();
		  return userMessage;
		
		
	}
	

		public Users setUserBeanValues(ResultSet rs,Users user){
			try {
				if(rs.next()) {
					user.setFirstName(rs.getString("firstName"));			
					user.setLastName(rs.getString("lastName"));
					user.setPassword(rs.getString("password"));
					user.setUserId(rs.getString("iduser"));				
									
					return user;
				}				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				
				
				
			}
			return null;
		}
		
		
		
		/*public ArrayList<String> displayDefaultImage()
		{
			ArrayList<String> defaultImages = new ArrayList<String>();
			
			Database d=new Database();
			conn=d.connectToDatabase();
			  if (conn != null) 
			  {
				  try {
	              PreparedStatement prepstmt=conn.prepareStatement("select image from defaultImage;");
	              ResultSet rs = prepstmt.executeQuery();
	              System.out.println("Query"+prepstmt);
	              while(rs.next())
	              {
	              
	            	defaultImages.add(rs.getString("image"));
	            	  
	              }
	                  
				  }
	             
	             catch (SQLException e) {
					// TODO Auto-generated catch block
					userMessage.setMessage("Invalid Login Please try again!!!");
					e.printStackTrace();
				}
				  
	              
			  }
			  d.closeConnection();
			
			return defaultImages;
			
		}
		*/
		
		
		
		public ArrayList<Boards> showBoards(String userId,String Category)
		{
			
			Database d=new Database();
			conn=d.connectToDatabase();
			ArrayList<Boards> listOfBoards=new ArrayList<Boards>();
			  if (conn != null) 
			  {
				  try {
	              PreparedStatement prepstmt=conn.prepareStatement("select bf.idboard,b.name,b.description from boardsFollowed bf,board b where bf.idboard=b.idboard and bf.iduser=?");
	              prepstmt.setString(1,userId);
	              ResultSet rs = prepstmt.executeQuery();
	              System.out.println("Query"+prepstmt);
	              
	              while(rs.next())
	              {
	            	  
	            	  Boards boards= new Boards();
	            	  boards.setBoardId(rs.getInt("idboard"));
	            	  boards.setCategory(rs.getString("category"));
	            	  boards.setDescription(rs.getString("description"));
	            	  boards.setName(rs.getString("name"));
		              
	            	  listOfBoards.add(boards);
	            	  
	              }
	            	
	            	  
	              }
				  catch(Exception e)
				  {
					  e.printStackTrace();
				  }
	            
			  d.closeConnection();	
		}
			  return listOfBoards;	
		}
		
		
		
		
		public String createBoard(String userId,Boards boards)
		
		{
			Database d=new Database();
			conn=d.connectToDatabase();
			
			String message=null;
			String boardname=boards.getName();
			String description=boards.getDescription();
			String category=boards.getCategory();
						
			 if (conn != null) 
			  {
				  try {
					  
					  stmt=conn.createStatement();
					  ResultSet rs;
					  String sql="insert into boards values ('"+boardname+"','"+description+"','"+category+"')";
					  
	              stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
	              rs=stmt.getGeneratedKeys();
	              
	            if(rs.next())
	            {
	            	System.out.println("inside resultset.next");
	            	int boardId=rs.getInt(1);
	            	ResultSet r=stmt.executeQuery("insert into userboards values('"+boardId+"','"+userId+"')");
	            	if(r.next())
	            	{
	            		System.out.println("inserted sucessfully");
	            		message="Board Created";
	            	}
	            	else
	            	{
	            		message="error";
	            		System.out.println("could not update userboard table");
	            	}
	            }
	            else
	            {
	            	message="error";
	            	System.out.println("could not insert most value");
	            }
	            
				}
				  catch (SQLException e) {
					// TODO Auto-generated catch block
					userMessage.setMessage("Invalid Login Please try again!!!");
					e.printStackTrace();
				}
			  }
			  d.closeConnection();
	
			return message;
		}
		
		
	
		
		public ArrayList<Tacks> showTacks(String userId,String boardName)
		{
			
			Database d=new Database();
			conn=d.connectToDatabase();
			ArrayList<Tacks> listOfTacks=new ArrayList<Tacks>();
			  if (conn != null) 
			  {
				  try {
					  
	              
	            
	              String query="select url,description,name,idtack from tacks where idtacks IN (select idtacks from tacksboard where idboard IN (select idboard from userboards where iduser LIKE "+userId+"AND idboard IN (select idboard from pinterest.board where name LIKE "+boardName+")))";
	              PreparedStatement prepstmt=conn.prepareStatement(query);
	              
	              prepstmt.setString(1,userId);
	              ResultSet rs = prepstmt.executeQuery();
	              System.out.println("Query"+prepstmt);
	              
	              while(rs.next())
	              {
	            	  
	            	  Tacks tacks= new Tacks();
	            	  tacks.setTackId(rs.getInt("idboard"));
	            	  tacks.setDescription(rs.getString("description"));
	            	  tacks.setName(rs.getString("name"));
	            	  		              
	            	  listOfTacks.add(tacks);
	            	  
	              }
	            	
	            	  
	              }
				  catch(Exception e)
				  {
					  e.printStackTrace();
				  }
	            
			  d.closeConnection();
			  
			
			
		}
		
			  return listOfTacks;
			  
			
			
		}
		
		
		
		public void deleteTack(String userId, String tackName) throws Exception
		{
			
			Database d=new Database();
			conn=d.connectToDatabase();
			
			ArrayList<Integer>tackIdList=new ArrayList<Integer>();
			ArrayList<Integer>boardIdList=new ArrayList<Integer>();
			ArrayList<Integer>boardNewId=new ArrayList<Integer>();
			ArrayList<Integer>deleteTackId=new ArrayList<Integer>();
			
			 if (conn != null) 
			  {
						try
						{
						PreparedStatement prepstmt=conn.prepareStatement("select idtacks from tacks where name=?");
						prepstmt.setString(1,tackName);
						ResultSet rs=prepstmt.executeQuery();
						while(rs.next())
						{
							tackIdList.add(rs.getInt("idtacks"));
						}
						for(int tl:tackIdList)
						{
						prepstmt=conn.prepareStatement("select idboard from tacksboard where idtacks=?");
						prepstmt.setInt(1,tl);
						rs=prepstmt.executeQuery();
						boardIdList.add(rs.getInt("idboard"));
						}
						for(int bl:boardIdList)
						{
						
						rs=stmt.executeQuery("select idboard from userboards where iduser LIKE "+ userId +" and idboard=" + bl + "" );
						boardNewId.add(rs.getInt("idboard"));
						}
							
						for(int bni:boardNewId)
						{
							prepstmt=conn.prepareStatement("select idtacks from tackboards where idboard=?");
							prepstmt.setInt(1,bni);
							rs=prepstmt.executeQuery();
							deleteTackId.add(rs.getInt("idtacks"));
						}
						
						
						for(int td:deleteTackId)
						{
						prepstmt=conn.prepareStatement("delete from tacksboard where idtacks=?");
						prepstmt.setInt(1,td);
						rs=prepstmt.executeQuery();
						}
						
						for(int td:deleteTackId)
						{
						//prepstmt=conn.prepareStatement("delete from tacks where idboard=?");
						prepstmt=conn.prepareStatement("delete from tacks where idtacks=?");
						prepstmt.setInt(1,td);
						rs=prepstmt.executeQuery();
						}  
					}
					catch(Exception e)
				  {
					  e.printStackTrace();
				  }
				  }
		}
	}







 



	
