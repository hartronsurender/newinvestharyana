'use strict';

describe('Controller Tests', function() {

    describe('Electric_load_type Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockElectric_load_type;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockElectric_load_type = jasmine.createSpy('MockElectric_load_type');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'Electric_load_type': MockElectric_load_type
            };
            createController = function() {
                $injector.get('$controller')("Electric_load_typeDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'investhryApp:electric_load_typeUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
