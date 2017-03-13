(function() {
    'use strict';

    angular
        .module('investhryApp')
        .controller('ProjectsitedetailDialogController', ProjectsitedetailDialogController);

    ProjectsitedetailDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Projectsitedetail'];

    function ProjectsitedetailDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Projectsitedetail) {
        var vm = this;

        vm.projectsitedetail = entity;
        vm.clear = clear;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.projectsitedetail.id !== null) {
                Projectsitedetail.update(vm.projectsitedetail, onSaveSuccess, onSaveError);
            } else {
                Projectsitedetail.save(vm.projectsitedetail, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('investhryApp:projectsitedetailUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setOwnership_document = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.ownership_document = base64Data;
                        projectsitedetail.ownership_documentContentType = $file.type;
                    });
                });
            }
        };

        vm.setLease_document = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.lease_document = base64Data;
                        projectsitedetail.lease_documentContentType = $file.type;
                    });
                });
            }
        };

        vm.setLandagreement_document = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.landagreement_document = base64Data;
                        projectsitedetail.landagreement_documentContentType = $file.type;
                    });
                });
            }
        };

        vm.setSiteplan_document = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.siteplan_document = base64Data;
                        projectsitedetail.siteplan_documentContentType = $file.type;
                    });
                });
            }
        };

        vm.setLocationplan_document = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.locationplan_document = base64Data;
                        projectsitedetail.locationplan_documentContentType = $file.type;
                    });
                });
            }
        };

        vm.setLinearstripplan_document = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.linearstripplan_document = base64Data;
                        projectsitedetail.linearstripplan_documentContentType = $file.type;
                    });
                });
            }
        };

        vm.setSitesituated_document = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.sitesituated_document = base64Data;
                        projectsitedetail.sitesituated_documentContentType = $file.type;
                    });
                });
            }
        };

        vm.setBuilding_plan_document = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.building_plan_document = base64Data;
                        projectsitedetail.building_plan_documentContentType = $file.type;
                    });
                });
            }
        };

        vm.setControlled_area_document = function ($file, projectsitedetail) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        projectsitedetail.controlled_area_document = base64Data;
                        projectsitedetail.controlled_area_documentContentType = $file.type;
                    });
                });
            }
        };

    }
})();
