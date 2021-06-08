package projet_SGBD;

import java.nio.file.attribute.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.nio.file.*;
import java.io.*;

import org.jdom2.*;
import org.jdom2.output.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.ListIterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/*import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;*/



public class DomPars {
	 public  DomPars() {
		 
	 }

		 //String casimp="(\\w+\\s+){0,3}cas import�s(\\w+\\s+){0,3}";
		// String casconct="(\\w+\\s+){0,3}cas contacts(\\w+\\s+){0,3}"; 
	//	 String testrealise="(\\w+\\s+){0,3}tests r�alis�s(\\w+\\s+){0,3}";
		// String contactsuivi="(\\w+\s+){0,3}contacts suivis(\\w+\\s+){0,3}";
		// String communautair="(\\w+\\s+){0,12} communautaire(\\w+\\s+){0,3}";
		// String gueri="(\\w+\\s+){0,6}n�gatifs et d�clar�s gu�ris(\\w+\\s+){0,3}";
		// String casdeces="(\\w+\\s+){0,10}d�c�s(\\w+\\s+){0,10}";
			/*Pattern pattern=Pattern.compile(ces,Pattern.CASE_INSENSITIVE);
			Matcher matcher= pattern.matcher(c);
					   if(matcher.find()) {
						   String sous_trait=matcher.group(1);
						   System.out.println("Je lai enfin trouver: "+matcher.group(1));*/
		 //Pour les cas sous traitement		 
	 public String Datcom(String c) {
		 String date = null;
		 String day = "(lundi|mardi|mercredi|jeudi|vendredi|samedi|dimanche)";
		   String []mois= {"janvier","fevrier","mars","avril","mai","juin","juillet","aout","septembre","octobre","novembre","decembre"};  
				    String word = "\\s(lundi|mardi|mercredi|jeudi|vendredi|samedi|dimanche)\\s(\\d*)\\s+(\\w*)\\s+(\\d{4})";
		
		 Pattern pattern=Pattern.compile(word,Pattern.CASE_INSENSITIVE);
			Matcher matcher= pattern.matcher(c);
					   if(matcher.find()) {
						  date=matcher.group();
		
	}
					   int i=0;int t=0;
				      for(String m:mois) {
				    	  i++;
				    	  //System.out.println(m);
				    	  if(matcher.group(3).toLowerCase().equals(m)) {
				    		  t=i;
				    		  

				    	  } 

						
					   }
				      System.out.println(matcher.group(3).toLowerCase());
				      String jou=matcher.group(2).toLowerCase();
				      String joucov;
				      if(jou.length()==1) {
				    	  joucov="0"+matcher.group(2).toLowerCase();
				      }
				      else
				    	  joucov=matcher.group(2).toLowerCase();
				    //  System.out.println("liimoyyoy"+matcher.group(2).toLowerCase());
					   return joucov+"-"+matcher.group(3).toLowerCase()+"-"+matcher.group(4).toLowerCase();
		 
	 }
		public double Soustrait(String c) {
			 double sous_trait=0;
			 String soustrait="(\\w+\\s+){0,3}sous traitement(\\w+\\s+){0,3}";	
			 Pattern pattern=Pattern.compile(soustrait,Pattern.CASE_INSENSITIVE);
				Matcher matcher= pattern.matcher(c);
						   if(matcher.find()) {
							  sous_trait=Double.parseDouble(matcher.group(1));
			
		}
			        
					  
					return sous_trait;
	}
		//Pour les Cas positifs
		public double casposit(String c) {
			  double cas_posit=0;
			 String casposit="(\\w+\\s+){0,3}sont revenus positifs(\\w+\\s+){0,3}";
			 Pattern pattern=Pattern.compile(casposit,Pattern.CASE_INSENSITIVE);
				Matcher matcher= pattern.matcher(c);
						   if(matcher.find()) {
							   cas_posit=Double.parseDouble(matcher.group(1));
			
		}
			        
						   return cas_posit;
	}
		//Pour les cas importees
		public double casimpor(String c) {
			 double cas_imp=0;
			 String casimp="(\\w+\\s+){0,3}cas importes(\\w+\\s+){0,3}";
			 Pattern pattern=Pattern.compile(casimp,Pattern.CASE_INSENSITIVE);
				Matcher matcher= pattern.matcher(c);
						   if(matcher.find()) {
							 cas_imp=Double.parseDouble(matcher.group(1));
			
		}
						   return cas_imp;				  					   
	}
		//Pour cas contact
		public double casconct(String c) {
			 double cas_cont=0;
			 String casconct="(\\d+)\\s+{0,3}cas contacts(\\w+\\s+){0,3}"; 
			 Pattern pattern=Pattern.compile(casconct,Pattern.CASE_INSENSITIVE);
				Matcher matcher= pattern.matcher(c);
						   if(matcher.find()) {
							  // System.out.println("sfddddddddddddd");
							  cas_cont=Double.parseDouble(matcher.group(1));
			
		}
						   return cas_cont;
	}
		//Pour le nombre de test realise
		public double testrealise(String c) {
			double test_real=0;
			 String testrealise="(\\d+)\\s+tests\\s+realises";
			 Pattern pattern=Pattern.compile(testrealise,Pattern.CASE_INSENSITIVE);
				Matcher matcher= pattern.matcher(c);
						   if(matcher.find()) {
							  // System.out.println("sfddddddddddddd");
							 test_real=Double.parseDouble(matcher.group(1));
			 
		}
						   return test_real;
	}
		//Pour le nombre de cas contact suvie
				public double mortotal(String c) {
					double cas_cont_suiv=0;
					 String contactsuivi="(\\w+\\s+)décédés";
					 Pattern pattern=Pattern.compile(contactsuivi,Pattern.CASE_INSENSITIVE);
						Matcher matcher= pattern.matcher(c);
								   if(matcher.find()) {
									   System.out.println("sfddddddddddddd");
									   cas_cont_suiv=Double.parseDouble(matcher.group(1));
					 
				}
								   return cas_cont_suiv;
			}
				//Pour le nombre de cas communautaire
				public double cascommunautaire(String c) {
					double cas_comunautair=0;
					 String communautair="(\\d+)\\s+cas issus de la transmission communautaire";
					 Pattern pattern=Pattern.compile(communautair,Pattern.CASE_INSENSITIVE);
						Matcher matcher= pattern.matcher(c);
								   if(matcher.find()) {
									   cas_comunautair=Double.parseDouble(matcher.group(1));
					 
				}
								   return cas_comunautair;
			}	
				//Pour le nombre de cas gueri
				public double casgueri(String c) {
					double casd;
					 String gueri="(\\d*)\\s*patients suivis ont été";
					 Pattern pattern=Pattern.compile(gueri,Pattern.CASE_INSENSITIVE);
						Matcher matcher= pattern.matcher(c);
								   if(matcher.find()) {
									   double cas_gueri=Double.parseDouble(matcher.group(1));
										casd=cas_gueri;
				}
								   else
									   casd=20;
								 
							
								   return 	casd;
			}
				
				
				//Pour le nombre de cas gueri
				public void casgueri2(String c) {
			
					 String gueri="(\\d+)\\s+patients suivis ont été";
					 Pattern pattern=Pattern.compile(gueri,Pattern.CASE_INSENSITIVE);
					 System.out.println(pattern);
						Matcher matcher= pattern.matcher(c);
								   if(matcher.find()) {
										String  cas_gueri=matcher.group(1);
										 System.out.println("cas_gueri2: "+cas_gueri);
				}
								   else
										 System.out.println("Rie");
								  
								 
			}
				//Pour le nombre de cas decedes
				public double casdeces(String c) {
					double cas_deces=0;
					String casdeces="(\\d+)\\s+ décés ont été enregistrés";
					 Pattern pattern=Pattern.compile(casdeces,Pattern.CASE_INSENSITIVE);
						Matcher matcher= pattern.matcher(c);
								   if(matcher.find()) {
									   cas_deces=Double.parseDouble(matcher.group(1));
					 
				}
								   System.out.println("casDecedes: "+cas_deces);
								   return cas_deces;
			}	
				public String casregions(String c) {
					String val="";
					String u="";
					 String[]  regions={"Dakar","Thies","Diourbel","Fatick","Kaolack","Kaffrine","Touba","Kolda","Tamba","Ziguinchor","Saint-Louis","Matam","S�dhiou"};
					   String regions_dict ;
					    for (String region :regions) {
					        String  gueri =  "("+region+")\\s(\\d).+cas|.?("+region+").?;|(\\d+).a.*?("+region+")";
					       
					       
					        Pattern pattern=Pattern.compile(gueri,Pattern.MULTILINE);
							Matcher matcher= pattern.matcher(c);
					       int chiffre = 0;
					        if(matcher.find()) {
					        	String  result_chiffre = matcher.group().replace(" a","-");
					        	  String  s= result_chiffre.replace(" cas","");
					        	  String g=s.replace(" ","");
					        	  String r=g.replace("\\d+\\w+ ","\\d+-\\w+");
					        	
					        	val+=" "+g;
					        	//System.out.println("Je lai enfin trouver" + result_chiffre);
					        }  		
					            		
					        else 
					         chiffre=0;
					    }
					    String lk=val.substring(0,6);
					    u=lk.replace(" ","");
					    System.out.println(u+"\n" +val);
					    String v;
						if(val.length()>8) {
					    String b=val.substring(9,val.length());
					    String f=val.substring(6,8);
					    System.out.println("f"+f);
					    String t=f+"-"+u;
					    System.out.println("t"+t);
					    String h=t.replace(" - ","-");
					    System.out.println("h"+h);
					    String x=h+" "+b;
					    String r= x.replace("-",":");
					       v= r.replace(" ",":");
					       System.out.println(v);
					    }
					    else {
					    	 String f=val.substring(6,7);
					    	  String t=f+":"+u;
					       v= t.replace(" ","");
					    	  
					    	
					    }
					   // StringBuilder strb = new StringBuilder(f);
					   //String v=strb.reverse().toString();
					    return v;
				
				}
				public String casDep(String c) {
					String val="";
					  String dep="(\\d*)\\s+a\\s(\\w*)\\s*(.*)\\s*;$";
				   		//"(-[0-9]{2,}+a\\w+.+);$";
				   		
				   Pattern patt=Pattern.compile(dep,Pattern.MULTILINE|Pattern.CASE_INSENSITIVE);
				   Matcher mat= patt.matcher(c);
				   if(mat.find()) {
					   
					   String  result_chiffre  = mat.group().replace(" a","");
					   String  s= result_chiffre.replace(" cas","");
				   	
				     	String r=s.replace(",","");
				     	String v=r.replace(";","");
				     	 String g=v.replace("et ","");
				    			String z=g.replace("  "," ");
				   	val+=" "+z;
					  
				  
				   }  
					
				   else
				   {
					   System.out.println("Je ne lai pas trouver dep"); 
					   val=" 00 Dakar";
				   }
				   System.out.println("Je lai enfin trouver dep" +val);
				   return val;
				}
			
				public ArrayList<String> Jsr(String c) {
					ArrayList<String> liste = new ArrayList<String>();
					ArrayList<Integer> lis = new ArrayList<Integer>();
	
					String var2= casregions(c);
			//System.out.println(var2);
					 
					
					   // char t= var2.charAt(i);
					   // b +=c;
					   String [] v=var2.split(":");
					  String jk;
					  int i,p;
					   for(i=0;i<v.length-1;i++){	
						   p=Integer.parseInt(v[i]);
						   i++;
					     jk=v[i];
					    
					    // p=parseInt(v[i]);
					    
					     liste.add(jk);
					     
					    	   //System.out.println("dsawsfsggfar");
					    	 lis.add(p);
					       }
					   ListIterator li = liste.listIterator();
					   while (li.hasNext()) {
					        System.out.println(li.next());
					   }
					   ListIterator m = lis.listIterator();
					   while (m.hasNext()) {
					        System.out.println(m.next());
					   }
					   
					   
                        return liste;
				}
				
				public void Jsonr(String c) {
					casgueri2(c);
					 String o=casregions(c);
					//Separer le nombre de cas avec la regions correspondante
					
					ArrayList<String> liste = new ArrayList<String>();
					ArrayList<Integer> lis = new ArrayList<Integer>();
					 String [] v=o.split(":");
					 System .out.println(v);
					  String jk;
					  int i,p;
					   for(i=0;i<v.length-1;i++){	
						 
						   p=Integer.parseInt(v[i]);
						   i++;
					     jk=v[i];
					    
					    // p=parseInt(v[i]);
					    
					     liste.add(jk);
					     
					    	   //System.out.println("dsawsfsggfar");
					    	 lis.add(p);
					       }
					   ListIterator li = liste.listIterator();
					   ListIterator m = lis.listIterator();
					   //while (li.hasNext()) {
					     //   System.out.println(li.next());
					   //}
					  // ListIterator m = lis.listIterator();
					//   while (m.hasNext()) {
					  //      System.out.println(m.next());
					   //}
					   
					
					
					//fin
					   
						//Separer le nombre de cas avec le departement correspondant
					   
					   
					   ArrayList<String> Dep = new ArrayList<String>();
					   String result_dep=casDep(c);
						 String [] l=result_dep.split(" ");
						  String j;
						  int y,u = Integer.parseInt(l[1]);
						   for(y=2;y<l.length;y++){	
							   //u=Integer.parseInt(v[0]);
							
						     j=l[y];
						    
						    // p=parseInt(v[i]);
						    
						     Dep.add(j);
						     
						       }
						   ListIterator listdep = Dep.listIterator();
					   
					   //findep
					   
					   
					String datecom= Datcom(c);
					String n=datecom.substring(3,datecom.length());
					//System.out.println(n);
				   Double	cascommunautaire=cascommunautaire(c);
				    Double Soustrait = Soustrait(c);
			       	Double casgueri= casgueri(c);	
				   Double mortotal=mortotal(c);
					Double casimpor=casimpor(c);
					Double casdeces=casdeces(c);
					Double testrealise=testrealise(c);
					Double casconct=casconct(c);
					Double casposit=casposit(c);
					 String var= casDep(c);
		     //ajouter donnees dans la base de donnees
					Test.addcom(datecom, Soustrait,casgueri,mortotal,casimpor,casdeces,testrealise,casconct,casposit,cascommunautaire);
				//fin ajout	 
						// System.out.println(var);
						 String[] dep=var.split("\\s");
						    File file = new File("C:/Users/JIbri/Desktop/db/json/"+n+".json");
						    JSONObject ob = new JSONObject();
					if(file.isFile()) {
						  JSONObject objet = new JSONObject();
						  
					        // Ajout du tableau
					        try {
					            // Ajout du tableau
					        	
					        		
						        	 while (listdep.hasNext()){
						        		 String dep_name;
						        		 dep_name=(String) listdep.next();
						        		 System.out.println("Voici l erreur: ");
						         Test.addDep( dep_name,u);    
						      	objet.put( dep_name,u);
						     // String realnamdep=(String) listdep.next();
						        	 }
						        	
					            ob.put("DEPARTEMENT", objet);
					        } catch(JSONException e) {
					            System.err.println("Erreur lors de l'insertion du tableau.");
					            System.err.println(e);
					            System.exit(-1);
					        }
					 
					        
					        /*String var2= casregions(c);
							// System.out.println(var);
							 String[] reg=var2.split("\\s");*/
                      
                         
                          int h=0;
                          JSONObject cas = new JSONObject();
                          cas.put("datecomt", datecom);
                          cas.put("Soustrait", Soustrait);
                          
                          cas.put("casgueri", casgueri);
                          cas.put("mortotal", mortotal);
                          cas.put("casimpor", casimpor);
                          cas.put("casdeces", casdeces);
                          cas.put("cascommunautaire", cascommunautaire);
                          cas.put("testrealise", testrealise);
                          cas.put("casconct", casconct);
                          cas.put("casposit", casposit);
							  JSONObject objetregion = new JSONObject();
							  
						        // Ajout du tableau
						        try {
						        	int nbcasreg;
						        	String name_reg;
						        	 while (li.hasNext()&& m.hasNext()) {
						        		 nbcasreg=(int) m.next();
						        		 name_reg= (String) li.next();
						        	objetregion .put(name_reg,nbcasreg);
						        	 Test.addReg(name_reg,nbcasreg);
						        }} catch(JSONException e) {
						            System.err.println("Erreur lors de l'insertion du tableau.");
						            System.err.println(e);
						            System.exit(-1);
						        }
					        
					        // Création du fichier de sortie
					        FileWriter fs=null;
					        try {
					            fs = new FileWriter("C:/Users/JIbri/Desktop/db/json/"+n+".json",true);
					        } catch(IOException e) {
					            System.err.println("Erreur lors de l'ouverture du fichier '" +n+ "'.");
					            System.err.println(e);
					            System.exit(-1);
					        }
					 
					        // Sauvegarde dans le fichier
					        try {
					        	cas.write(fs, 3, 0);
					        	objetregion.write(fs, 3, 0);
					            ob.write(fs, 3, 0);
					            fs.flush();
					        } catch(IOException e) {
					            System.err.println("Erreur lors de l'écriture dans le fichier.");
					            System.err.println(e);
					            System.exit(-1);
					        }
					      
					 
					        // Fermeture du fichier
					        try {
					            fs.close();
					        } catch(IOException e) {
					            System.err.println("Erreur lors de la fermeture du fichier.");
					            System.err.println(e);
					            System.exit(-1);
					        }
					 
					        System.out.println("Le fichier '" +n+ "' a été généré.");
				
					}
			else
			{
					  JSONObject objet = new JSONObject();
					  
				        // Ajout du tableau
				        try {
				        	 String name_dep;
				        	 while (listdep.hasNext()){
				        		 name_dep=(String) listdep.next();
							      	objet.put(name_dep,u);
							      	 // String realnamdep=(String) listdep.next();
							      	Test.addDep( name_dep,u);
							        	 }
				        	  ob.put("DEPARTEMENT", objet);
				            //objet.put("DEPARTEMENT", new JSONArray(dep));
				        } catch(JSONException e) {
				            System.err.println("Erreur lors de l'insertion du tableau.");
				            System.err.println(e);
				            System.exit(-1);
				        }
				 
				   
				        String var2= casregions(c);
						// System.out.println(var);
						 String[] reg=var2.split("\\s");
						  JSONObject cas = new JSONObject();
						   cas.put("datecomt", datecom);
                          cas.put("Soustrait", Soustrait);
                          
                          cas.put("casgueri", casgueri);
                          cas.put("mortotal", mortotal);
                          cas.put("casimpor", casimpor);
                          cas.put("casdeces", casdeces);
                          cas.put("cascommunautaire", cascommunautaire);
                          cas.put("testrealise", testrealise);
                          cas.put("casconct", casconct);
                          cas.put("casposit", casposit);
                          
						
						  JSONObject objetregion = new JSONObject();
						  int nbrcasreg;
						  String name_reg;
					        // Ajout du tableau
						  // Ajout du tableau
					        try {
					        	 while (li.hasNext()&& m.hasNext()) {
					        		 name_reg=(String) li.next(); 
					        		 nbrcasreg=(int) m.next();
					        	objetregion .put(name_reg, nbrcasreg);
					        	
					        	 Test.addReg(name_reg, nbrcasreg);
					        }} catch(JSONException e) {
					            System.err.println("Erreur lors de l'insertion du tableau.");
					            System.err.println(e);
					            System.exit(-1);
					        }
				        
				        // Création du fichier de sortie
				        FileWriter fs = null;
				        try {
				            fs = new FileWriter("C:/Users/JIbri/Desktop/db/json/"+n+".json");
				        } catch(IOException e) {
				            System.err.println("Erreur lors de l'ouverture du fichier '" +n+ "'.");
				            System.err.println(e);
				            System.exit(-1);
				        }
				 
				        // Sauvegarde dans le fichier
				        try {
				        	cas.write(fs, 3, 0);
				        	objetregion.write(fs, 3, 0);
				            ob.write(fs, 3, 0);
				            fs.flush();
				        } catch(IOException e) {
				            System.err.println("Erreur lors de l'écriture dans le fichier.");
				            System.err.println(e);
				            System.exit(-1);
				        }
				 
				        // Fermeture du fichier
				        try {
				            fs.close();
				        } catch(IOException e) {
				            System.err.println("Erreur lors de la fermeture du fichier.");
				            System.err.println(e);
				            System.exit(-1);
				        }
				 
				        System.out.println("Le fichier '" +n+ "' a été généré.");
	
				}
			
			
				}
}
