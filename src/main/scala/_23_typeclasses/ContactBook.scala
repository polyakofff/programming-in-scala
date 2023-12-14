package _23_typeclasses

case class Address(
    street: String,
    city: String,
    state: String,
    zip: Int
                  )

case class Phone(
    countyCode: Int,
    phoneNumber: Long
                )

case class Contact(
    name: String,
    addresses: List[Address],
    phones: List[Phone]
                  )

case class AddressBook(contacts: List[Contact])

object Address {
  given addressSerializer: JsonSerializer[Address] with
    override def serialize(a: Address) =
      import ToJsonMethods.{toJson as asJson}
      s"""|{
          |  "street": ${a.street.asJson},
          |  "city": ${a.city.asJson},
          |  "state": ${a.state.asJson},
          |  "zip": ${a.zip.asJson}
          |}""".stripMargin
}

object Phone {
  given phoneSerializer: JsonSerializer[Phone] with
    override def serialize(p: Phone) =
      import ToJsonMethods.{toJson as asJson}
      s"""|{
          |  "countryCode": ${p.countyCode.asJson},
          |  "phoneNumber": ${p.phoneNumber.asJson}
          |}""".stripMargin
}

object Contact {
  given contactSerializer: JsonSerializer[Contact] with
    override def serialize(c: Contact) =
      import ToJsonMethods.{toJson as asJson}
      s"""|{
          |  "name": ${c.name.asJson},
          |  "addresses": ${c.addresses.asJson},
          |  "phones": ${c.phones.asJson}
          |}""".stripMargin
}

object AddressBook {
  given addressBookSerializer: JsonSerializer[AddressBook] with
    override def serialize(a: AddressBook) =
      import ToJsonMethods.{toJson as asJson}
      s"""|{
          |  "contacts": ${a.contacts.asJson}
          |}""".stripMargin
}
