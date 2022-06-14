<?xml version="1.0" encoding="UTF-8"?>
<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema" xmlns:tns="xml.${DTO_PACKAGE}.${PROJECT_PACKAGE}"
           targetNamespace="xml.${DTO_PACKAGE}.${PROJECT_PACKAGE}" elementFormDefault="qualified">

<#list entities as entity>
    <xs:element name="Get${entity.name}Request">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="id" type="xs:long"/>
            </xs:sequence>
        </xs:complexType>
    </xs:element>

    <xs:element name="Get${entity.name}Response">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="${entity.name}" type="tns:${entity.name}"/>
            </xs:sequence>
        </xs:complexType>
    </xs:element>

    <xs:element name="Save${entity.name}Request">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="${entity.name}" type="tns:${entity.name}"/>
            </xs:sequence>
        </xs:complexType>
    </xs:element>

    <xs:element name="Save${entity.name}Response">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="id" type="xs:long"/>
            </xs:sequence>
        </xs:complexType>
    </xs:element>

    <xs:element name="Update${entity.name}Request">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="${entity.name}" type="tns:${entity.name}"/>
            </xs:sequence>
        </xs:complexType>
    </xs:element>

    <xs:element name="Update${entity.name}Response">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="${entity.name}" type="tns:${entity.name}"/>
            </xs:sequence>
        </xs:complexType>
    </xs:element>

    <xs:element name="Delete${entity.name}Request">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="id" type="xs:long"/>
            </xs:sequence>
        </xs:complexType>
    </xs:element>

    <xs:element name="Delete${entity.name}Response">
        <xs:complexType>
            <xs:sequence>
                <xs:element name="${entity.name}" type="tns:${entity.name}"/>
            </xs:sequence>
        </xs:complexType>
    </xs:element>

    <xs:complexType name="${entity.name}">
        <xs:sequence>
            <xs:element name="id" type="xs:long"/>
            <#list entity.fields as field>
                <xs:element name="${field.name}" type="xs:${field.type}"/>
            </#list>
        </xs:sequence>
    </xs:complexType>

</#list>
</xs:schema>