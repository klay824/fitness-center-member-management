package javaproject;

import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.Scanner;

public class MembershipManagement {
    final private Scanner reader = new Scanner(System.in);
    
    private int getIntInput () {
        int choice = 0;
        
        while (choice == 0) {
            try {
                choice = reader.nextInt();
                
                if (choice == 0)
                    throw new InputMismatchException();
                reader.nextLine();
            }
            catch (InputMismatchException e) {
                reader.nextLine();
                System.out.println("\nERROR: INVALID INPUT. Please try again:");
            }
        }
        return choice;
    }
    
    private void printClubOptions () {
        System.out.println("\nClub Mercury");
        System.out.println("Club Neptune");
        System.out.println("Club Jupiter");
        System.out.println("Multi Clubs");
    }
    
    public int getChoice() {
        int choice;
        
        System.out.println("WELCOME TO OZONE FITNESS CENTER");
        System.out.println("===============================");
        System.out.println("1) Add Member");
        System.out.println("2) Remove Member");
        System.out.println("3) Display Member Information");
        
        System.out.print("\nPlease select an option (or enter -1 to quit.");
        choice = getIntInput();
        return choice;
    }
    
    public String addMembers(LinkedList<Member> m) {
        String name;
        int club;
        String mem;
        double fees;
        int memberID;
        Member mbr;
        Calculator<Integer> cal;
        
        System.out.print("\nPlease enter the member's name:");
        name = reader.nextLine();
        
        printClubOptions();
        System.out.print("\nPlease enter the members' clubID:");
        club = getIntInput();
        
        while(club < 1 || club > 4) {
            System.out.print("\nThe input is invalid. Please enter a valid clubID. Please try again:");
            club = getIntInput();
        }
        
        if (m.size() > 0)
            memberID = m.getLast().getMemberID() + 1;
        else
            memberID = 1;
        
        if (club != 4) {
            cal = (n) -> {
                switch (n) {
                    case 1:
                        return 900;
                    case 2:
                        return 950;
                    case 3:
                        return 1000;
                    default:
                        return -1;
                }
            };
            fees = cal.calculateFees(club);
            mbr = new SingleClubMember('S', memberID, name, fees, club);
            m.add(mbr);
            
            mem = mbr.toString();
            
            System.out.println("\nSTATUS: Single Club Member added\n");            
        }
        else {
            cal = (n) -> {
                if (n == 4)
                    return 1200;
                else
                    return -1;
            };
            
            fees = cal.calculateFees(club);
            
            mbr = new MultiClubMember('M', memberID, name, fees, 100);
            
            m.add(mbr);
            
            mem = mbr.toString();
            
            System.out.println("\nSTATUS: Multi Club Member added\n");
        }
        return mem;
    }
    
    public void removeMember(LinkedList<Member> m) {
        int memberID;
        System.out.print("\nPlease enter the ID of the member you wish to remove");
        
        memberID = getIntInput();
        
        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).getMemberID() == memberID) {
                m.remove(i);
                System.out.println("\nThe member has been removed.\n");
                return;
            }
        }
        System.out.println("\nMember not found.");
    }
    
    public void printMemberInfo(LinkedList<Member> m) {
        int memberID;
        System.out.print("\nPlease enter member ID to display information.");
        
        memberID = getIntInput();
        
        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).getMemberID() == memberID) {
                String[] memberInfo = m.get(i).toString().split(",");
                System.out.println("\n\nMember type is: " + memberInfo[0]);
                System.out.println("Member ID is: " + memberInfo[1]);;
                System.out.println("Member Name is: " + memberInfo[2]);
                System.out.println("Membership fees are: " + memberInfo[3]);
                
                if (memberInfo[0].equals("S")) {
                    System.out.println("Club ID is: " + memberInfo[4]);
                } else {
                    System.out.println("Membership Points are: " + memberInfo[4]);
                }
                return;
            }
        }
        System.out.println("\nMember ID not found.\n");
    }
}
