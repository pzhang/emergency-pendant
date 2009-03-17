/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: C:\\Users\\Dan\\workspace\\emergency-pendant\\src\\com\\android\\pendant\\InputWrapperInterface.aidl
 */
package com.android.pendant;
import java.lang.String;
import android.os.RemoteException;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Binder;
import android.os.Parcel;
public interface InputWrapperInterface extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.android.pendant.InputWrapperInterface
{
private static final java.lang.String DESCRIPTOR = "com.android.pendant.InputWrapperInterface";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an InputWrapperInterface interface,
 * generating a proxy if needed.
 */
public static com.android.pendant.InputWrapperInterface asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = (android.os.IInterface)obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.android.pendant.InputWrapperInterface))) {
return ((com.android.pendant.InputWrapperInterface)iin);
}
return new com.android.pendant.InputWrapperInterface.Stub.Proxy(obj);
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
case TRANSACTION_location:
{
data.enforceInterface(DESCRIPTOR);
float[] _result = this.location();
reply.writeNoException();
reply.writeFloatArray(_result);
return true;
}
case TRANSACTION_tacResponse:
{
data.enforceInterface(DESCRIPTOR);
boolean _result = this.tacResponse();
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_xcel:
{
data.enforceInterface(DESCRIPTOR);
float[] _result = this.xcel();
reply.writeNoException();
reply.writeFloatArray(_result);
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.android.pendant.InputWrapperInterface
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
public float[] location() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
float[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_location, _data, _reply, 0);
_reply.readException();
_result = _reply.createFloatArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public boolean tacResponse() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_tacResponse, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
public float[] xcel() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
float[] _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_xcel, _data, _reply, 0);
_reply.readException();
_result = _reply.createFloatArray();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
}
static final int TRANSACTION_location = (IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_tacResponse = (IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_xcel = (IBinder.FIRST_CALL_TRANSACTION + 2);
}
public float[] location() throws android.os.RemoteException;
public boolean tacResponse() throws android.os.RemoteException;
public float[] xcel() throws android.os.RemoteException;
}
