package rs.ac.uns.ftn.informatika.osa.pr04;


/**
* rs/ac/uns/ftn/informatika/osa/pr04/HelloHelper.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from rs/ac/uns/ftn/informatika/osa/pr04/Hello.idl
* Wednesday, June 26, 2013 11:02:27 AM CEST
*/

abstract public class HelloHelper
{
  private static String  _id = "IDL:rs/ac/uns/ftn/informatika/osa/pr04/Hello:1.0";

  public static void insert (org.omg.CORBA.Any a, rs.ac.uns.ftn.informatika.osa.pr04.Hello that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static rs.ac.uns.ftn.informatika.osa.pr04.Hello extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (rs.ac.uns.ftn.informatika.osa.pr04.HelloHelper.id (), "Hello");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static rs.ac.uns.ftn.informatika.osa.pr04.Hello read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_HelloStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, rs.ac.uns.ftn.informatika.osa.pr04.Hello value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static rs.ac.uns.ftn.informatika.osa.pr04.Hello narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof rs.ac.uns.ftn.informatika.osa.pr04.Hello)
      return (rs.ac.uns.ftn.informatika.osa.pr04.Hello)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      rs.ac.uns.ftn.informatika.osa.pr04._HelloStub stub = new rs.ac.uns.ftn.informatika.osa.pr04._HelloStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static rs.ac.uns.ftn.informatika.osa.pr04.Hello unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof rs.ac.uns.ftn.informatika.osa.pr04.Hello)
      return (rs.ac.uns.ftn.informatika.osa.pr04.Hello)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      rs.ac.uns.ftn.informatika.osa.pr04._HelloStub stub = new rs.ac.uns.ftn.informatika.osa.pr04._HelloStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
