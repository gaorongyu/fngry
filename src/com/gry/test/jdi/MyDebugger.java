package com.gry.test.jdi;

import com.sun.jdi.*;
import com.sun.jdi.connect.AttachingConnector;
import com.sun.jdi.connect.Connector;
import com.sun.jdi.connect.IllegalConnectorArgumentsException;
import com.sun.jdi.event.*;
import com.sun.jdi.request.BreakpointRequest;
import com.sun.jdi.request.EventRequest;
import com.sun.jdi.request.EventRequestManager;
import com.sun.jdi.request.MethodEntryRequest;
import com.sun.tools.jdi.ObjectReferenceImpl;
import com.sun.tools.jdi.SocketAttachingConnector;

import java.io.IOException;
import java.util.List;
import java.util.Map;


/**
 *
 * 基于JDI的调试工具
 *
 * Created by gaorongyu on 14-9-25.
 *
 */
public class MyDebugger {

    /**
     * 虚拟机是否退出
     */
    private boolean vmExit = false;

    /**
     * 目标虚拟机
     */
    private VirtualMachine vm;

    /**
     * 调试事件集合
     */
    private EventSet eventSet;


    public void debug() throws Exception {
        // 连接目标虚拟机
        vm = attach("localhost", "7070");
        // 注册事件请求
        createRequest();
        // 轮询结果
        handleEvent();
    }

    private VirtualMachine attach(String hostName, String targetPort) throws IOException,
            IllegalConnectorArgumentsException {
        VirtualMachineManager vmManager = Bootstrap.virtualMachineManager();
        SocketAttachingConnector connector = getSocketAttachConnector(vmManager);

        Map<String, Connector.Argument> argumentMap = connector.defaultArguments();

        Connector.Argument host = argumentMap.get("hostname");
        Connector.Argument port = argumentMap.get("port");
        Connector.Argument timeout = argumentMap.get("timeout");

        host.setValue(hostName);
        port.setValue(targetPort);
        timeout.setValue("3000");

        return connector.attach(argumentMap);
    }

    private void createRequest() throws AbsentInformationException {
        EventRequestManager eventRequestManager = vm.eventRequestManager();

        // 创建方法进入事件
        MethodEntryRequest methodEntryRequest = eventRequestManager.createMethodEntryRequest();
        methodEntryRequest.addClassFilter("com.tqmall.saint.web.common.SecureValidHandler");
        methodEntryRequest.setSuspendPolicy(EventRequest.SUSPEND_EVENT_THREAD); // 挂起线程
        methodEntryRequest.enable();

        // 创建断点事件
        List<ReferenceType> classesByName = vm.classesByName("com.tqmall.saint.web.common.SecureValidHandler");
        ReferenceType referenceType = classesByName.get(0);
        List<Location> locations = referenceType.locationsOfLine(110);
        BreakpointRequest breakpointRequest = eventRequestManager.createBreakpointRequest(locations.get(0));
        breakpointRequest.setSuspendPolicy(EventRequest.SUSPEND_EVENT_THREAD);
        breakpointRequest.enable();

        List<ReferenceType> classesByName1 = vm.classesByName("com.tqmall.saint.web.common.SecureValidHandler");
        ReferenceType referenceType1 = classesByName1.get(0);
        List<Location> locations1 = referenceType1.locationsOfLine(114);
        BreakpointRequest breakpointRequest1 = eventRequestManager.createBreakpointRequest(locations1.get(0));
        breakpointRequest1.setSuspendPolicy(EventRequest.SUSPEND_EVENT_THREAD);
        breakpointRequest1.enable();
    }

    private void handleEvent() throws InterruptedException, IncompatibleThreadStateException, AbsentInformationException {
        EventQueue eventQueue = vm.eventQueue();

        while(true) {
            if(vmExit) {
                break;
            }
            eventSet = eventQueue.remove();
            EventIterator iterator = eventSet.eventIterator();
            while(iterator.hasNext()) {
                handle(iterator.nextEvent());
            }
        }
    }

    private void handle(Event event) throws IncompatibleThreadStateException, AbsentInformationException {
        if(event instanceof MethodEntryEvent) {
            MethodEntryEvent methodEntryEvent = (MethodEntryEvent) event;
            ThreadReference threadReference = methodEntryEvent.thread();

            StackFrame stackFrame = threadReference.frame(0);
            List<LocalVariable> localVariables = methodEntryEvent.method().arguments();
            LocalVariable localVariable = localVariables.get(0);
            Value value = stackFrame.getValue(localVariable);

//            ObjectReferenceImpl objectValue = (ObjectReferenceImpl) value;
//            Field field = getFieldByName(objectValue.referenceType().allFields(), "value");
//
//            System.out.println("参数: \n    " + "The param times is " + objectValue.getValue(field)
//                    + " of " + value.type().name());
//            System.out.println("堆栈: \n    " + threadReference.frames());
            eventSet.resume();
        } else if(event instanceof BreakpointEvent) {
            BreakpointEvent breakpointEvent = (BreakpointEvent) event;
            ThreadReference threadReference = breakpointEvent.thread();

            StackFrame stackFrame = threadReference.frame(0);
            LocalVariable localVariable = stackFrame.visibleVariableByName("requestData");
            Value value = stackFrame.getValue(localVariable);
            String str = ((StringReference) value).value();


            System.out.println("变量:\n    " + "The local variable second is " + str
                    + " of " + value.type().name() + "\n");
            eventSet.resume();
        } else if(event instanceof VMDisconnectEvent) {
            vmExit = true;
        } else {
            eventSet.resume(); // 恢复线程
        }
    }

    /**
     *
     * 获取引用类型的属性
     *
     * @param fields 引用类型属性列表
     * @param name   属性名称
     *
     * @return Field
     *
     */
    private Field getFieldByName(List<Field> fields, String name) {
        for(Field field : fields) {
            if(field.name().equals(name)) {
                return field;
            }
        }
        return null;
    }

    /**
     * 获取SocketAttach连接器
     */
    private SocketAttachingConnector getSocketAttachConnector(VirtualMachineManager vmManager) {
        for(AttachingConnector connector : vmManager.attachingConnectors()) {
            if("com.sun.jdi.SocketAttach".equals(connector.name())) {
                return (SocketAttachingConnector) connector;
            }
        }
        return null;
    }

}
