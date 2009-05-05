/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\Dan\\workspace\\emergency-pendant\\src\\com\\android\\pendant\\OutputWrapperInterface.aidl
 */
package com.android.pendant;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
public interface OutputWrapperInterface extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.android.pendant.OutputWrapperInterface
{
private static final java.lang.String DESCRIPTOR = "com.android.pendant.OutputWrapperInterface";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an OutputWrapperInterface interface,
 * generating a proxy if needed.
 */
public static com.android.pendant.OutputWrapperInterface asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.android.pendant.OutputWrapperInterface))) {
return ((com.android.pendant.OutputWrapperInterface)iin);
}
return new com.android.pendant.OutputWrapperInterface.Stub.Proxy(obj);
}
public android.os.IBinder asBinder()
{
return this;
}
public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_transmit:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.transmit(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_notification:
{
data.enforceInterface(DESCRIPTOR);
this.notification();
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.android.pendant.OutputWrapperInterface
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
public void transmit(java.lang.String msg) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(msg);
mRemote.transact(Stub.TRANSACTION_transmit, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
public void notification() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_notification, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_transmit = (IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_notification = (IBinder.FIRST_CALL_TRANSACTION + 1);
}
public void transmit(java.lang.String msg) throws android.os.RemoteException;
public void notification() throws android.os.RemoteException;
}
