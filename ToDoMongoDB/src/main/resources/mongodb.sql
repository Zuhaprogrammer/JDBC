use my_db
switched to db my_db
use to_do
switched to db to_do
db.createCollection("users", {})
{ ok: 1 }
db.users.drop();
true
db.createCollection("users", {})
{ ok: 1 }
db.users.drop();
true
db.createCollection("users", {
	validator: {
		$jsonSchema: {
			bsonType: "object",
			required: [],
			properties: {
			}
		}
	}
})
MongoServerError[FailedToParse]: $jsonSchema keyword 'required' cannot be an empty array
db.createCollection("users", {
	validator: {
		$jsonSchema: {
			bsonType: "object",
			required: ["username", "password", "email", "phoneNumber", "active", "createdDate", "updatedDate"],
			properties: {
				username: {
          bsonType: "string",
          description: "Majburiy matn turi"
        },
				password: {
          bsonType: "string",
          description: "Majburiy matn turi"
        },
				email: {
          bsonType: "string",
          description: "Majburiy matn turi"
        },
				phoneNumber: {
          bsonType: "string",
          description: "Majburiy matn turi"
        },
				active: {
          bsonType: "boolean",
          description: "Majburiy matn turi"
        },
				createdDate: {
          bsonType: "date",
          description: "Majburiy matn turi"
        },
				updatedDate: {
          bsonType: "date",
          description: "Majburiy matn turi"
        }
			}
		}
	},
	validationLevel: "moderate",
  validationAction: "error"
});
MongoServerError[BadValue]: Unknown type name alias: boolean
db.createCollection("users", {
	validator: {
		$jsonSchema: {
			bsonType: "object",
			required: ["username", "password", "email", "phoneNumber", "active", "createdDate", "updatedDate"],
			properties: {
				username: {
          bsonType: "string",
          description: "Majburiy matn turi"
        },
				password: {
          bsonType: "string",
          description: "Majburiy matn turi"
        },
				email: {
          bsonType: "string",
          description: "Majburiy matn turi"
        },
				phoneNumber: {
          bsonType: "string",
          description: "Majburiy matn turi"
        },
				active: {
          bsonType: "bool",
          description: "Majburiy matn turi"
        },
				createdDate: {
          bsonType: "date",
          description: "Majburiy matn turi"
        },
				updatedDate: {
          bsonType: "date",
          description: "Majburiy matn turi"
        }
			}
		}
	},
	validationLevel: "moderate",
  validationAction: "error"
});
{ ok: 1 }
db.createCollection("toDo", {
	validator: {
		$jsonSchema: {
			bsonType: "object",
			required: ["title", "active", "deadline", "userId", "createdDate", "updatedDate"],
			properties: {
				title: {
          bsonType: "string",
          description: "Majburiy matn turi"
        },
				avtive: {
          bsonType: "bool",
          description: "Majburiy matn turi"
        },
				deadline: {
          bsonType: "date",
          description: "Majburiy matn turi"
        },
				userId: {
          bsonType: "objectId",
          description: "Majburiy matn turi"
        },
				createdDate: {
          bsonType: "date",
          description: "Majburiy matn turi"
        },
				updatedDate: {
          bsonType: "date",
          description: "Majburiy matn turi"
        }
			}
		}
	},
	validationLevel: "moderate",
  validationAction: "error"
});
{ ok: 1 }
db.users.createIndex({username: 1}, {unique: true});
username_1
db.users.getIndexes;
[Function: getIndexes] AsyncFunction {
  apiVersions: [ 1, Infinity ],
  serverVersions: [ '3.2.0', '999.999.999' ],
  returnsPromise: true,
  topologies: [ 'ReplSet', 'Sharded', 'LoadBalanced', 'Standalone' ],
  returnType: { type: 'unknown', attributes: {} },
  deprecated: false,
  platforms: [ 'Compass', 'Browser', 'CLI' ],
  isDirectShellCommand: false,
  acceptsRawInput: false,
  shellCommandCompleter: undefined,
  help: [Function (anonymous)] Help
}
db.users.getIndexes();
[
  { v: 2, key: { _id: 1 }, name: '_id_' },
  { v: 2, key: { username: 1 }, name: 'username_1', unique: true }
]
db.users.createIndex({email: 1}, {unique: true});
email_1
db.users.getIndexes();
[
  { v: 2, key: { _id: 1 }, name: '_id_' },
  { v: 2, key: { username: 1 }, name: 'username_1', unique: true },
  { v: 2, key: { email: 1 }, name: 'email_1', unique: true }
]
use admin
switched to db admin
db.getUsers();
{ users: [], ok: 1 }
db.createUser({
  user: "admin",
  pwd: "zuhaadmin19", // admin parolini yozing
  roles: [{ role: "dbOwner", db: "to_do" }]
});
{ ok: 1 }
db.getUsers();
{
  users: [
    {
      _id: 'admin.admin',
      userId: UUID('7ed43639-4076-4a67-a577-764d330412bc'),
      user: 'admin',
      db: 'admin',
      roles: [Array],
      mechanisms: [Array]
    }
  ],
  ok: 1
}
use to_do
switched to db to_do
db.createUser({
  user: "zuhaadmin",
  pwd: "zuhaadmin19", // admin parolini yozing
  roles: [{ role: "dbOwner", db: "to_do" }]
});