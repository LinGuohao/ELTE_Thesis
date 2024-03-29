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
 *   !proto.com.guohaohome.moviedb.proto.MovieID>}
 */
const methodDescriptor_MoviedbService_InsertMovie = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/InsertMovie',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.InfoRequest,
  proto.com.guohaohome.moviedb.proto.MovieID,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.InfoRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.MovieID.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.InfoRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.MovieID)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.MovieID>|undefined}
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
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.MovieID>}
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
 *   !proto.com.guohaohome.moviedb.proto.MovieID,
 *   !proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 */
const methodDescriptor_MoviedbService_DeleteMovieByID = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/DeleteMovieByID',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.MovieID,
  proto.com.guohaohome.moviedb.proto.BooleanResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.MovieID} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.BooleanResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.MovieID} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.BooleanResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.BooleanResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.deleteMovieByID =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/DeleteMovieByID',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_DeleteMovieByID,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.MovieID} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.deleteMovieByID =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/DeleteMovieByID',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_DeleteMovieByID);
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
 *   !proto.com.guohaohome.moviedb.proto.LineList,
 *   !proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 */
const methodDescriptor_MoviedbService_InsertLine = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/InsertLine',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.LineList,
  proto.com.guohaohome.moviedb.proto.BooleanResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.LineList} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.BooleanResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.LineList} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.BooleanResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.BooleanResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.insertLine =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/InsertLine',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_InsertLine,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.LineList} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.insertLine =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/InsertLine',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_InsertLine);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.deleteLineRequest,
 *   !proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 */
const methodDescriptor_MoviedbService_DeleteLine = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/DeleteLine',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.deleteLineRequest,
  proto.com.guohaohome.moviedb.proto.BooleanResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.deleteLineRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.BooleanResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.deleteLineRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.BooleanResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.BooleanResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.deleteLine =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/DeleteLine',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_DeleteLine,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.deleteLineRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.deleteLine =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/DeleteLine',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_DeleteLine);
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


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.VerificationRequest,
 *   !proto.com.guohaohome.moviedb.proto.UserInfo>}
 */
const methodDescriptor_MoviedbService_AuthenticateUser = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/AuthenticateUser',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.VerificationRequest,
  proto.com.guohaohome.moviedb.proto.UserInfo,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.VerificationRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.UserInfo.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.VerificationRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.UserInfo)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.UserInfo>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.authenticateUser =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/AuthenticateUser',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_AuthenticateUser,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.VerificationRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.UserInfo>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.authenticateUser =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/AuthenticateUser',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_AuthenticateUser);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.TextUploadRequest,
 *   !proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 */
const methodDescriptor_MoviedbService_UploadTextToOSS = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/UploadTextToOSS',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.TextUploadRequest,
  proto.com.guohaohome.moviedb.proto.BooleanResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.TextUploadRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.BooleanResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.TextUploadRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.BooleanResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.BooleanResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.uploadTextToOSS =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/UploadTextToOSS',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_UploadTextToOSS,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.TextUploadRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.uploadTextToOSS =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/UploadTextToOSS',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_UploadTextToOSS);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.FileUploadRequest,
 *   !proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 */
const methodDescriptor_MoviedbService_UploadFileToOSS = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/UploadFileToOSS',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.FileUploadRequest,
  proto.com.guohaohome.moviedb.proto.BooleanResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.FileUploadRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.BooleanResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.FileUploadRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.BooleanResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.BooleanResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.uploadFileToOSS =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/UploadFileToOSS',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_UploadFileToOSS,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.FileUploadRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.uploadFileToOSS =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/UploadFileToOSS',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_UploadFileToOSS);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.FileDeleteRequest,
 *   !proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 */
const methodDescriptor_MoviedbService_DeleteFileFromOSS = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/DeleteFileFromOSS',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.FileDeleteRequest,
  proto.com.guohaohome.moviedb.proto.BooleanResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.FileDeleteRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.BooleanResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.FileDeleteRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.BooleanResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.BooleanResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.deleteFileFromOSS =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/DeleteFileFromOSS',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_DeleteFileFromOSS,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.FileDeleteRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.deleteFileFromOSS =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/DeleteFileFromOSS',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_DeleteFileFromOSS);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.MusicUploadRequest,
 *   !proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 */
const methodDescriptor_MoviedbService_UploadMusicToOSS = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/UploadMusicToOSS',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.MusicUploadRequest,
  proto.com.guohaohome.moviedb.proto.BooleanResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.MusicUploadRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.BooleanResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.MusicUploadRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.BooleanResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.BooleanResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.uploadMusicToOSS =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/UploadMusicToOSS',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_UploadMusicToOSS,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.MusicUploadRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.uploadMusicToOSS =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/UploadMusicToOSS',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_UploadMusicToOSS);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.InfoByIDRequest,
 *   !proto.com.guohaohome.moviedb.proto.CommentListResponse>}
 */
const methodDescriptor_MoviedbService_GetCommentByMovieID = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/GetCommentByMovieID',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.InfoByIDRequest,
  proto.com.guohaohome.moviedb.proto.CommentListResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.InfoByIDRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.CommentListResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.InfoByIDRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.CommentListResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.CommentListResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.getCommentByMovieID =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetCommentByMovieID',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetCommentByMovieID,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.InfoByIDRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.CommentListResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.getCommentByMovieID =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetCommentByMovieID',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetCommentByMovieID);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.InfoByIDRequest,
 *   !proto.com.guohaohome.moviedb.proto.CommentListResponse>}
 */
const methodDescriptor_MoviedbService_GetCommentByUserName = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/GetCommentByUserName',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.InfoByIDRequest,
  proto.com.guohaohome.moviedb.proto.CommentListResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.InfoByIDRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.CommentListResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.InfoByIDRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.CommentListResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.CommentListResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.getCommentByUserName =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetCommentByUserName',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetCommentByUserName,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.InfoByIDRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.CommentListResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.getCommentByUserName =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetCommentByUserName',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetCommentByUserName);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.CommentInfo,
 *   !proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 */
const methodDescriptor_MoviedbService_InsertComment = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/InsertComment',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.CommentInfo,
  proto.com.guohaohome.moviedb.proto.BooleanResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.CommentInfo} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.BooleanResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.CommentInfo} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.BooleanResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.BooleanResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.insertComment =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/InsertComment',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_InsertComment,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.CommentInfo} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.insertComment =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/InsertComment',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_InsertComment);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.InfoByIDRequest,
 *   !proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 */
const methodDescriptor_MoviedbService_DeleteComment = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/DeleteComment',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.InfoByIDRequest,
  proto.com.guohaohome.moviedb.proto.BooleanResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.InfoByIDRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.BooleanResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.InfoByIDRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.BooleanResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.BooleanResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.deleteComment =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/DeleteComment',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_DeleteComment,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.InfoByIDRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.deleteComment =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/DeleteComment',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_DeleteComment);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.UsernameRequest,
 *   !proto.com.guohaohome.moviedb.proto.UserLikeListResponse>}
 */
const methodDescriptor_MoviedbService_GetUserLikes = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/GetUserLikes',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.UsernameRequest,
  proto.com.guohaohome.moviedb.proto.UserLikeListResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.UsernameRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.UserLikeListResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.UsernameRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.UserLikeListResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.UserLikeListResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.getUserLikes =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetUserLikes',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetUserLikes,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.UsernameRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.UserLikeListResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.getUserLikes =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetUserLikes',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetUserLikes);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.UserLikeInfo,
 *   !proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 */
const methodDescriptor_MoviedbService_InsertUserLike = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/InsertUserLike',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.UserLikeInfo,
  proto.com.guohaohome.moviedb.proto.BooleanResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.UserLikeInfo} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.BooleanResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.UserLikeInfo} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.BooleanResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.BooleanResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.insertUserLike =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/InsertUserLike',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_InsertUserLike,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.UserLikeInfo} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.insertUserLike =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/InsertUserLike',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_InsertUserLike);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.UserLikeInfo,
 *   !proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 */
const methodDescriptor_MoviedbService_DeleteUserLike = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/DeleteUserLike',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.UserLikeInfo,
  proto.com.guohaohome.moviedb.proto.BooleanResponse,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.UserLikeInfo} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.BooleanResponse.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.UserLikeInfo} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.BooleanResponse)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.BooleanResponse>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.deleteUserLike =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/DeleteUserLike',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_DeleteUserLike,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.UserLikeInfo} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.BooleanResponse>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.deleteUserLike =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/DeleteUserLike',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_DeleteUserLike);
};


/**
 * @const
 * @type {!grpc.web.MethodDescriptor<
 *   !proto.com.guohaohome.moviedb.proto.UsernameRequest,
 *   !proto.com.guohaohome.moviedb.proto.InfoList>}
 */
const methodDescriptor_MoviedbService_GetFavoriteMovieList = new grpc.web.MethodDescriptor(
  '/com.guohaohome.moviedb.proto.MoviedbService/GetFavoriteMovieList',
  grpc.web.MethodType.UNARY,
  proto.com.guohaohome.moviedb.proto.UsernameRequest,
  proto.com.guohaohome.moviedb.proto.InfoList,
  /**
   * @param {!proto.com.guohaohome.moviedb.proto.UsernameRequest} request
   * @return {!Uint8Array}
   */
  function(request) {
    return request.serializeBinary();
  },
  proto.com.guohaohome.moviedb.proto.InfoList.deserializeBinary
);


/**
 * @param {!proto.com.guohaohome.moviedb.proto.UsernameRequest} request The
 *     request proto
 * @param {?Object<string, string>} metadata User defined
 *     call metadata
 * @param {function(?grpc.web.RpcError, ?proto.com.guohaohome.moviedb.proto.InfoList)}
 *     callback The callback function(error, response)
 * @return {!grpc.web.ClientReadableStream<!proto.com.guohaohome.moviedb.proto.InfoList>|undefined}
 *     The XHR Node Readable Stream
 */
proto.com.guohaohome.moviedb.proto.MoviedbServiceClient.prototype.getFavoriteMovieList =
    function(request, metadata, callback) {
  return this.client_.rpcCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetFavoriteMovieList',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetFavoriteMovieList,
      callback);
};


/**
 * @param {!proto.com.guohaohome.moviedb.proto.UsernameRequest} request The
 *     request proto
 * @param {?Object<string, string>=} metadata User defined
 *     call metadata
 * @return {!Promise<!proto.com.guohaohome.moviedb.proto.InfoList>}
 *     Promise that resolves to the response
 */
proto.com.guohaohome.moviedb.proto.MoviedbServicePromiseClient.prototype.getFavoriteMovieList =
    function(request, metadata) {
  return this.client_.unaryCall(this.hostname_ +
      '/com.guohaohome.moviedb.proto.MoviedbService/GetFavoriteMovieList',
      request,
      metadata || {},
      methodDescriptor_MoviedbService_GetFavoriteMovieList);
};


module.exports = proto.com.guohaohome.moviedb.proto;

