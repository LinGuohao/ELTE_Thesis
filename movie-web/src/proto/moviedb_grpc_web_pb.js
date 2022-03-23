/**
 * @fileoverview gRPC-Web generated client stub for com.guohaohome.moviedb.proto
 * @enhanceable
 * @public
 */

// GENERATED CODE -- DO NOT EDIT!


/* eslint-disable */
// @ts-nocheck



const grpc = {};
grpc.web = require('grpc-web');


var google_protobuf_empty_pb = require('google-protobuf/google/protobuf/empty_pb.js')
const proto = {};
proto.com = {};
proto.com.guohaohome = {};
proto.com.guohaohome.moviedb = {};
proto.com.guohaohome.moviedb.proto = require('./moviedb_pb.js');

/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?grpc.web.ClientOptions} options
 * @constructor
 * @struct
 * @final
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options.format = 'binary';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname;

};


/**
 * @param {string} hostname
 * @param {?Object} credentials
 * @param {?grpc.web.ClientOptions} options
 * @constructor
 * @struct
 * @final
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient =
    function(hostname, credentials, options) {
  if (!options) options = {};
  options.format = 'binary';

  /**
   * @private @const {!grpc.web.GrpcWebClientBase} The client
   */
  this.client_ = new grpc.web.GrpcWebClientBase(options);

  /**
   * @private @const {string} The hostname
   */
  this.hostname_ = hostname;

};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.google.protobuf.Empty,
 *   !proto.com.guohaohome.moviedb.proto.AllMovieIDListResponse>}
 */
const methodDescriptor_MoviedbService_GetAllID = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/GetAllID',
  grpc.web.MethodType.UNARY,
  google_protobuf_empty_pb.Empty,
  proto.com.guohaohome.moviedb.proto.AllMovieIDListResponse,
  /**
   * @param {!proto.google.protobuf.Empty} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.AllMovieIDListResponse.deserializeBinary
);


/**
 * @param {!proto.google.protobuf.Empty} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.AllMovieIDListResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.AllMovieIDListResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.getAllID =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetAllID',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetAllID,
      callback);
};


/**
 * @param {!proto.google.protobuf.Empty} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.AllMovieIDListResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.getAllID =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetAllID',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetAllID);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.InfoByIDRequest,
 *   !proto.com.guohaohome.moviedb.proto.InfoResponse>}
 */
const methodDescriptor_MoviedbService_GetInfoByID = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/GetInfoByID',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.InfoByIDRequest,
  proto.com.guohaohome.moviedb.proto.InfoResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.InfoByIDRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.InfoResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.InfoByIDRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.InfoResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.InfoResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.getInfoByID =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetInfoByID',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetInfoByID,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.InfoByIDRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.InfoResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.getInfoByID =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetInfoByID',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetInfoByID);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.InfoRequest,
 *   !proto.google.protobuf.Empty>}
 */
const methodDescriptor_MoviedbService_UpdateByID = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/UpdateByID',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.InfoRequest,
  google_protobuf_empty_pb.Empty,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.InfoRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  google_protobuf_empty_pb.Empty.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.InfoRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.google.protobuf.Empty)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.google.protobuf.Empty>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.updateByID =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/UpdateByID',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_UpdateByID,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.InfoRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.google.protobuf.Empty>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.updateByID =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/UpdateByID',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_UpdateByID);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.InfoRequest,
 *   !proto.google.protobuf.Empty>}
 */
const methodDescriptor_MoviedbService_InsertMovie = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/InsertMovie',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.InfoRequest,
  google_protobuf_empty_pb.Empty,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.InfoRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  google_protobuf_empty_pb.Empty.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.InfoRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.google.protobuf.Empty)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.google.protobuf.Empty>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.insertMovie =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/InsertMovie',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_InsertMovie,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.InfoRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.google.protobuf.Empty>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.insertMovie =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/InsertMovie',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_InsertMovie);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.ObjectListRequest,
 *   !proto.com.guohaohome.moviedb.proto.ObjectListResponse>}
 */
const methodDescriptor_MoviedbService_GetOssObjectList = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/GetOssObjectList',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.ObjectListRequest,
  proto.com.guohaohome.moviedb.proto.ObjectListResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.ObjectListRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.ObjectListResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.ObjectListRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.ObjectListResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.ObjectListResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.getOssObjectList =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetOssObjectList',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetOssObjectList,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.ObjectListRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.ObjectListResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.getOssObjectList =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetOssObjectList',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetOssObjectList);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.InfoByIDRequest,
 *   !proto.com.guohaohome.moviedb.proto.LineListResponse>}
 */
const methodDescriptor_MoviedbService_GetLines = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/GetLines',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.InfoByIDRequest,
  proto.com.guohaohome.moviedb.proto.LineListResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.InfoByIDRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.LineListResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.InfoByIDRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.LineListResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.LineListResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.getLines =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetLines',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetLines,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.InfoByIDRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.LineListResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.getLines =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetLines',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetLines);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.InfoByIDRequest,
 *   !proto.com.guohaohome.moviedb.proto.MusicListResponse>}
 */
const methodDescriptor_MoviedbService_GetMusics = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/GetMusics',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.InfoByIDRequest,
  proto.com.guohaohome.moviedb.proto.MusicListResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.InfoByIDRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.MusicListResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.InfoByIDRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.MusicListResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.MusicListResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.getMusics =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetMusics',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetMusics,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.InfoByIDRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.MusicListResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.getMusics =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetMusics',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetMusics);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.UsernameRequest,
 *   !proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 */
const methodDescriptor_MoviedbService_JudgeUsername = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/JudgeUsername',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.UsernameRequest,
  proto.com.guohaohome.moviedb.proto.BooleanResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.UsernameRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.BooleanResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.UsernameRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.BooleanResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.BooleanResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.judgeUsername =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/JudgeUsername',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_JudgeUsername,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.UsernameRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.judgeUsername =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/JudgeUsername',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_JudgeUsername);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.UserInfo,
 *   !proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 */
const methodDescriptor_MoviedbService_InsertUser = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/InsertUser',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.UserInfo,
  proto.com.guohaohome.moviedb.proto.BooleanResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.UserInfo} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.BooleanResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.UserInfo} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.BooleanResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.BooleanResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.insertUser =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/InsertUser',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_InsertUser,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.UserInfo} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.insertUser =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/InsertUser',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_InsertUser);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.UsernameRequest,
 *   !proto.com.guohaohome.moviedb.proto.UserInfo>}
 */
const methodDescriptor_MoviedbService_GetUserByUserName = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/GetUserByUserName',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.UsernameRequest,
  proto.com.guohaohome.moviedb.proto.UserInfo,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.UsernameRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.UserInfo.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.UsernameRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.UserInfo)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.UserInfo>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.getUserByUserName =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetUserByUserName',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetUserByUserName,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.UsernameRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.UserInfo>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.getUserByUserName =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetUserByUserName',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetUserByUserName);
};


module.exports = proto.com.guohaohome.moviedb.proto;

