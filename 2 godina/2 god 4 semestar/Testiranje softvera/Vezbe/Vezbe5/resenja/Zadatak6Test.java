package unittesting.calculator;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Zadatak6Test {
	
	@Test
	public void test1(){
		System.out.println("--> izvrsio se test_1");
	}
	
	@Test
	public void test2(){
		System.out.println("--> izvrsio se test_2");
	}
	
	/*
	 * Poziva se pre svakog testa (@Test)
	 */
	@BeforeMethod
	public void preMetode(){
		System.out.println("--> sada ce se desiti poziv nekog testa");
	}
	
	
	/*
	 * Poziva se nakon svakog test case-a (<test> u XML-u)
	 */
	@AfterTest
	public void posleTestCase(){
		System.out.println("==> sada se zavrsio neki test");
	}
	
	@BeforeClass
	public void preKlase(){
		System.out.println("@@> sada ce se izvrsiti cela klasa sa svim svojim testovima");
	}
	
	@AfterClass
	public void posleKlase(){
		System.out.println("@@> sada su se zavrsili svi testovi iz neke klase");
	}
	
	@Test(groups = {"grupa1"})
	public void test3(){
		System.out.println("--> izvrsio se test_3");
	}
	
	@Test(groups = {"grupa1"})
	public void test4(){
		System.out.println("--> izvrsio se test_4");
	}
	
	@Test(groups = {"grupa2"})
	public void test5(){
		System.out.println("--> izvrsio se test_5");
	}
	
}